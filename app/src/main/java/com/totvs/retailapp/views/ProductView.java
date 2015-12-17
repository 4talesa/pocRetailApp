package com.totvs.retailapp.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.parse.ParseUser;
import com.totvs.retailapp.AppRetailActivity;
import com.totvs.retailapp.R;
import com.totvs.retailapp.adapters.ProductTwoWayViewAdapter;
import com.totvs.retailapp.daos.ShoppingCartItemDao;
import com.totvs.retailapp.models.CategoryModel;
import com.totvs.retailapp.models.ProductModel;
import com.totvs.retailapp.models.ShoppingCartItemModel;
import com.totvs.retailapp.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;

/**
 * Created by rond.borges on 10/12/2015.
 */
public class ProductView extends AppRetailViewAbsctrat<ProductModel> {

    protected ImageButton buttonProductDetailArrowLeft;
    protected ImageButton buttonProductDetailArrowRight;
    protected ImageView imageViewProductDetail;
    protected TextView labelProductDetailName;
    protected TextView labelProductDetailUnit;
    protected TextView labelProductDetailPrice;
    protected TextView editTextProductDetailAmount;
    protected Button buttonProductDetailMinus;
    protected Button buttonProductDetailPlus;
    protected Button buttonProductDetailAddToCart;
    protected TwoWayView twoWayViewRelatedProduct;
    protected ProductTwoWayViewAdapter adapter;
    protected ShoppingCartItemView shoppingCartItemView;
    protected ShoppingCartItemDao shoppingCartItemDao;

    public ProductView(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void populateView(ProductModel model) {

        try {
            imageViewProductDetail = (ImageView) view.findViewById(R.id.imageViewProductDetail);
            labelProductDetailName = (TextView) view.findViewById(R.id.labelProductDetailName);
            labelProductDetailUnit = (TextView) view.findViewById(R.id.labelProductDetailUnit);
            labelProductDetailPrice = (TextView) view.findViewById(R.id.labelProductDetailPrice);
            editTextProductDetailAmount = (TextView) view.findViewById(R.id.editTextProductDetailAmount);
            buttonProductDetailArrowLeft = (ImageButton) view.findViewById(R.id.buttonProductDetailArrowLeft);
            buttonProductDetailArrowRight = (ImageButton) view.findViewById(R.id.buttonProductDetailArrowRight);
            buttonProductDetailMinus = (Button) view.findViewById(R.id.buttonProductDetailMinus);
            buttonProductDetailPlus = (Button) view.findViewById(R.id.buttonProductDetailPlus);
            buttonProductDetailAddToCart = (Button) view.findViewById(R.id.buttonProductDetailAddToCart);

            buttonProductDetailArrowLeft.setEnabled(false);
            buttonProductDetailArrowRight.setEnabled(false);

            labelProductDetailName.setText(model.getName());
            labelProductDetailUnit.setText(model.getUnit());
            labelProductDetailPrice.setText(context.getResources().getString(R.string.app_label_dollar) + " " + String.format("%1$,.2f", model.getPrice()));

            buttonProductDetailAddToCart.setTag(model);

            Ion.with(imageViewProductDetail)
                    .fitCenter()
                    .load(model.getPictureUrl());

            buttonProductDetailMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateProductAmount(-1);
                }
            });

            buttonProductDetailPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateProductAmount(1);
                }
            });

            buttonProductDetailAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ProductModel productModel = (ProductModel) v.getTag();

                    ShoppingCartItemModel shoppingCartItemModel = new ShoppingCartItemModel(
                            ParseUser.getCurrentUser().getObjectId()+"."+productModel.getId()
                            , productModel.getId()
                            , ParseUser.getCurrentUser().getObjectId()
                            , productModel.getDetail()
                            , productModel.getUnit()
                            , productModel.getPrice()
                            , Double.parseDouble(editTextProductDetailAmount.getText().toString()) * productModel.getPrice()
                            , productModel.getPictureUrl()
                            , productModel.getCategory()
                            , productModel.getBrand()
                            , Double.parseDouble(editTextProductDetailAmount.getText().toString())
                            , "pending"
                    );

                    shoppingCartItemView = new ShoppingCartItemView(context, ((AppRetailActivity) context).getWindow().getDecorView().getRootView());
                    shoppingCartItemDao = new ShoppingCartItemDao(context, shoppingCartItemView);

                    shoppingCartItemDao.save( shoppingCartItemModel );

                }
            });

            adapter = new ProductTwoWayViewAdapter(new ArrayList<ProductModel>(), R.layout.product_thumb_item, context, new String[]{CategoryModel.CATEGORY, model.getIdCategory(), "1"});
            twoWayViewRelatedProduct = (TwoWayView) view.findViewById(R.id.twoWayViewRelatedProduct);
            twoWayViewRelatedProduct.setAdapter(adapter);
        }catch (Exception e){
            Log.d("ProductView", "Error:", e);
        }

    }

    private void updateProductAmount(Integer incValue){
        try {
            Integer currentAmount = Integer.decode(editTextProductDetailAmount.getText().toString());
            currentAmount = currentAmount + incValue;
            if (currentAmount < 0){
                currentAmount = 1;
            }
            editTextProductDetailAmount.setText(currentAmount.toString());
        }catch (Exception e){
            Log.d("updateProductAmount", "Error", e);
        }
    }

    @Override
    protected void updateJSONArray(JSONArray response) {
        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                populateView(ProductModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }
    }

}
