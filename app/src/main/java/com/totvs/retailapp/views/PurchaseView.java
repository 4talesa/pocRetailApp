package com.totvs.retailapp.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.totvs.retailapp.R;
import com.totvs.retailapp.adapters.PurchaseItemListViewAdapter;
import com.totvs.retailapp.models.PurchaseItemModel;
import com.totvs.retailapp.models.PurchaseModel;

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
    public void populateView(PurchaseModel model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        textViewPurchaseDate = (TextView) view.findViewById(R.id.textViewPurchaseDate);
        textViewPurchaseTitle = (TextView) view.findViewById(R.id.textViewPurchaseTitle);
        buttonPurchaseDetailAddAllToCart = (Button) view.findViewById(R.id.buttonPurchaseDetailAddAllToCart);
        listView = (ListView) view.findViewById(R.id.listViewPurchaseDetail);

        textViewPurchaseDate.setText(dateFormat.format(model.getDate()));
        textViewPurchaseTitle.setText(context.getResources().getString(R.string.app_label_purchase) + " #" + model.getId());

        purchaseItemAdapter = new PurchaseItemListViewAdapter(context, R.layout.purchase_detail_item, new ArrayList<PurchaseItemModel>(), new String[]{PurchaseModel.PURCHASE, model.getId()});
        listView.setAdapter(purchaseItemAdapter);
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
