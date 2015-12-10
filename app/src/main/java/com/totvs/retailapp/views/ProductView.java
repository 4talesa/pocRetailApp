package com.totvs.retailapp.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public ProductView(Context context, View view) {
        super(context, view);
    }

    @Override
    public void populateView(ProductModel model) {

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

        labelProductDetailName.setText(model.getDescription());
        labelProductDetailUnit.setText(model.getUnit());
        labelProductDetailPrice.setText(context.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getValue()));

        Ion.with(imageViewProductDetail)
                .fitCenter()
                .load(model.getUrlPicture());

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
                //// TODO: 10/12/2015 Implement the add to cart option
            }
        });

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
            System.out.println("Error - updateProductAmount: " + e.toString());
        }
    }

    @Override
    protected void updateJSONArray(JSONArray response) {
        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                ProductModel model =
                        new ProductModel(
                                object.getString("id")
                                , object.getString("name")
                                , context.getResources().getString(R.string.app_label_category)
                                , object.getString("pictureurl")
                                , 1.99
                                , object.getString("name")
                                , context.getResources().getString(R.string.app_label_product_preview_unit)
                        );

                populateView(model);

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }
    }

}
