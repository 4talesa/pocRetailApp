package com.totvs.retailapp.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseUser;
import com.totvs.retailapp.AppRetailActivity;
import com.totvs.retailapp.R;
import com.totvs.retailapp.adapters.PurchaseItemListViewAdapter;
import com.totvs.retailapp.daos.ShoppingCartItemDao;
import com.totvs.retailapp.models.PurchaseItemModel;
import com.totvs.retailapp.models.PurchaseModel;
import com.totvs.retailapp.models.ShoppingCartItemModel;
import com.totvs.retailapp.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rond.borges on 10/12/2015.
 */
public class PurchaseView extends AppRetailViewAbsctrat<PurchaseModel> {

    protected TextView textViewPurchaseDate;
    protected TextView textViewPurchaseTitle;
    protected Button buttonPurchaseDetailAddAllToCart;
    protected ListView listView;
    PurchaseItemListViewAdapter purchaseItemAdapter;

    public PurchaseView(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void populateView(PurchaseModel model) {

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

            textViewPurchaseDate = (TextView) view.findViewById(R.id.textViewPurchaseDate);
            textViewPurchaseTitle = (TextView) view.findViewById(R.id.textViewPurchaseTitle);
            buttonPurchaseDetailAddAllToCart = (Button) view.findViewById(R.id.buttonPurchaseDetailAddAllToCart);
            listView = (ListView) view.findViewById(R.id.listViewPurchaseDetail);

            textViewPurchaseDate.setText(dateFormat.format(model.getDate()));
            textViewPurchaseTitle.setText(context.getResources().getString(R.string.app_label_purchase) + " #" + model.getId());

            purchaseItemAdapter = new PurchaseItemListViewAdapter(context, R.layout.purchase_detail_item, new ArrayList<PurchaseItemModel>(), new String[]{PurchaseModel.PURCHASE, model.getId()});
            listView.setAdapter(purchaseItemAdapter);

            buttonPurchaseDetailAddAllToCart.setTag(model);
            buttonPurchaseDetailAddAllToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        PurchaseModel purchaseModel = (PurchaseModel) buttonPurchaseDetailAddAllToCart.getTag();
                        StoreModel.getInstance().setId(purchaseModel.getIdStore());

                        for(int i = 0; i<purchaseItemAdapter.getCount(); i++){

                            PurchaseItemModel productItemModel = (PurchaseItemModel) purchaseItemAdapter.getItem(i);

                            ShoppingCartItemModel shoppingCartItemModel = new ShoppingCartItemModel(
                                    ParseUser.getCurrentUser().getObjectId()+"."+productItemModel.getIdProduct()
                                    , productItemModel.getIdProduct()
                                    , ParseUser.getCurrentUser().getObjectId()+"."+ purchaseModel.getIdStore()
                                    , productItemModel.getDescription()
                                    , productItemModel.getUnit()
                                    , productItemModel.getUnitValue()
                                    , productItemModel.getTotalItem()
                                    , productItemModel.getPictureUrl()
                                    , productItemModel.getCategory()
                                    , productItemModel.getBrand()
                                    , productItemModel.getAmountPurchased()
                                    , productItemModel.getAmountRequested()
                                    , "pending"
                            );

                            ShoppingCartItemView shoppingCartItemView = new ShoppingCartItemView(context, ((AppRetailActivity) context).getWindow().getDecorView().getRootView());
                            ShoppingCartItemDao shoppingCartItemDao = new ShoppingCartItemDao(context, shoppingCartItemView);

                            shoppingCartItemDao.save( shoppingCartItemModel );
                        }
                    }catch (Exception e){
                        Log.d("PurchaseView", "Error:", e);
                    }
                }
            });
        }catch (Exception e){
            Log.d("PurchaseView", "Error:", e);
        }

    }

    @Override
    protected void updateJSONArray(JSONArray response) {
        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                populateView(PurchaseModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }
    }
}
