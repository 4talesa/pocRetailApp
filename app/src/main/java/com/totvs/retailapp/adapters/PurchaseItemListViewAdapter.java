package com.totvs.retailapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.ProductDetailActivity;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.ProductModel;
import com.totvs.retailapp.models.PurchaseItemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rond.borges on 25/11/2015.
 */
public class PurchaseItemListViewAdapter extends ListViewAdapterAbstract<PurchaseItemModel> {

    public PurchaseItemListViewAdapter(Context context, int layout, ArrayList<PurchaseItemModel> objects){
        super(context, layout, objects, "PurchaseItem");
    }

    public PurchaseItemListViewAdapter(Context context, int layout, ArrayList<PurchaseItemModel> objects, String[] urlFilters){
        super(context, layout, objects, "PurchaseItem", urlFilters);
    }

    @Override
    protected void populateView(View v, PurchaseItemModel model) {

        ImageView purchase_detail_item_picture = (ImageView) v.findViewById(R.id.purchase_detail_item_picture);
        TextView purchase_detail_item_description = (TextView) v.findViewById(R.id.purchase_detail_item_description);
        TextView purchase_detail_item_category = (TextView) v.findViewById(R.id.purchase_detail_item_category);
        TextView purchase_detail_item_brand = (TextView) v.findViewById(R.id.purchase_detail_item_brand);
        TextView purchase_detail_item_unit_label = (TextView) v.findViewById(R.id.purchase_detail_item_unit_label);
        TextView purchase_detail_item_unit_value = (TextView) v.findViewById(R.id.purchase_detail_item_unit_value);
        TextView purchase_detail_item_quantity_label = (TextView) v.findViewById(R.id.purchase_detail_item_quantity_label);
        TextView purchase_detail_item_quantity_value = (TextView) v.findViewById(R.id.purchase_detail_item_quantity_value);
        TextView purchase_detail_item_total_amount_label = (TextView) v.findViewById(R.id.purchase_detail_item_total_amount_label);
        TextView purchase_detail_item_total_amount_value = (TextView) v.findViewById(R.id.purchase_detail_item_total_amount_value);

        purchase_detail_item_description.setText(model.getDescription());
        purchase_detail_item_category.setText(model.getCategory());
        purchase_detail_item_brand.setText(model.getBrand());
        purchase_detail_item_unit_label.setText(model.getUnit());
        purchase_detail_item_unit_value.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getUnitValue()));
        purchase_detail_item_quantity_label.setText("Quantity");
        purchase_detail_item_quantity_value.setText(String.format("%1$,.2f", model.getAmountPurchased()));
        purchase_detail_item_total_amount_label.setText("Total amount");
        purchase_detail_item_total_amount_value.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getTotalItem()));

        Ion.with(purchase_detail_item_picture)
                .fitCenter()
                .load(model.getPictureUrl());

        purchase_detail_item_picture.setTag(model.getIdProduct());
        purchase_detail_item_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), ProductDetailActivity.class);

                it.putExtra(ProductModel.PRODUCT_ID, v.getTag().toString());

                v.getContext().startActivity(it);
            }
        });
    }

    @Override
    protected void updateJSONArray(JSONArray response){

        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                add(PurchaseItemModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }

    }

}
