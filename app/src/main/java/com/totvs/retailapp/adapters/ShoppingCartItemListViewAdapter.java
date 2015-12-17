package com.totvs.retailapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.ShoppingCartItemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rond.borges on 16/12/2015.
 */
public class ShoppingCartItemListViewAdapter extends ListViewAdapterAbstract<ShoppingCartItemModel> {

    public ShoppingCartItemListViewAdapter(Context context, int layout, ArrayList<ShoppingCartItemModel> objects){
        super(context, layout, objects, "ShoppingCartItem");
    }

    public ShoppingCartItemListViewAdapter(Context context, int layout, ArrayList<ShoppingCartItemModel> objects, String[] urlFilters){
        super(context, layout, objects, "ShoppingCartItem", urlFilters);
    }

    @Override
    protected void populateView(View v, ShoppingCartItemModel model) {

        ImageView shopping_cart_item_picture = (ImageView) v.findViewById(R.id.shopping_cart_item_picture);
        TextView shopping_cart_item_description = (TextView) v.findViewById(R.id.shopping_cart_item_description);
        TextView shopping_cart_item_category = (TextView) v.findViewById(R.id.shopping_cart_item_category);
        TextView shopping_cart_item_brand = (TextView) v.findViewById(R.id.shopping_cart_item_brand);
        TextView shopping_cart_item_unit_label = (TextView) v.findViewById(R.id.shopping_cart_item_unit_label);
        TextView shopping_cart_item_unit_value = (TextView) v.findViewById(R.id.shopping_cart_item_unit_value);
        TextView shopping_cart_item_quantity_label = (TextView) v.findViewById(R.id.shopping_cart_item_quantity_label);
        TextView shopping_cart_item_quantity_value = (TextView) v.findViewById(R.id.shopping_cart_item_quantity_value);
        TextView shopping_cart_item_total_amount_label = (TextView) v.findViewById(R.id.shopping_cart_item_total_amount_label);
        TextView shopping_cart_item_total_amount_value = (TextView) v.findViewById(R.id.shopping_cart_item_total_amount_value);

        shopping_cart_item_description.setText(model.getDescription());
        shopping_cart_item_category.setText(model.getCategory());
        shopping_cart_item_brand.setText(model.getBrand());
        shopping_cart_item_unit_label.setText(model.getUnit());
        shopping_cart_item_unit_value.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getUnitValue()));
        shopping_cart_item_quantity_label.setText("Quantity");
        shopping_cart_item_quantity_value.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getQuantity()));
        shopping_cart_item_total_amount_label.setText("Total amount");
        shopping_cart_item_total_amount_value.setText(v.getResources().getString(R.string.app_label_dollar) + " " + String.format("%1$,.2f", model.getAmount()));

        Ion.with(shopping_cart_item_picture)
                .fitCenter()
                .load(model.getPictureUrl());

    }

    @Override
    protected void updateJSONArray(JSONArray response){

        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                add(ShoppingCartItemModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }

    }

}
