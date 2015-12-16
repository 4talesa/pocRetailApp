package com.totvs.retailapp.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.totvs.retailapp.R;
import com.totvs.retailapp.adapters.PurchaseItemListViewAdapter;
import com.totvs.retailapp.adapters.ShoppingCartItemListViewAdapter;
import com.totvs.retailapp.models.ShoppingCartItemModel;
import com.totvs.retailapp.models.ShoppingCartModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rond.borges on 16/12/2015.
 */
public class ShoppingCartView extends AppRetailViewAbsctrat<ShoppingCartModel> {

    protected TextView textViewShoppingCartDate;
    protected TextView textViewShoppingCartTitle;
    protected Button buttonShoppingCartPlaceOrder;
    protected ListView listView;
    ShoppingCartItemListViewAdapter shoppingCartItemAdapter;

    public ShoppingCartView(Context context, View view) {
        super(context, view);
    }

    @Override
    public void populateView(ShoppingCartModel model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        textViewShoppingCartDate = (TextView) view.findViewById(R.id.textViewShoppingCartDate);
        textViewShoppingCartTitle = (TextView) view.findViewById(R.id.textViewShoppingCartTitle);
        buttonShoppingCartPlaceOrder = (Button) view.findViewById(R.id.buttonShoppingCartPlaceOrder);
        listView = (ListView) view.findViewById(R.id.listViewShoppingCart);

        textViewShoppingCartDate.setText(dateFormat.format(model.getDate()));
        textViewShoppingCartTitle.setText(context.getResources().getString(R.string.app_label_purchase) + " #" + model.getId());

        shoppingCartItemAdapter = new ShoppingCartItemListViewAdapter(context, R.layout.purchase_detail_item, new ArrayList<ShoppingCartItemModel>(), new String[]{ShoppingCartModel.SHOPPING_CART, model.getId()});
        listView.setAdapter(shoppingCartItemAdapter);
    }

    @Override
    protected void updateJSONArray(JSONArray response) {
        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                populateView(ShoppingCartModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }
    }

}
