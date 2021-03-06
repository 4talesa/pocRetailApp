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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class ProductListViewAdapter extends ListViewAdapterAbstract<ProductModel> {

    public ProductListViewAdapter(Context context, int layout, ArrayList<ProductModel> arrayList) {
        super(context, layout, arrayList, "Product");
    }

    @Override
    protected void populateView(View v, ProductModel model) {

        TextView product_thumb_item_description = (TextView) v.findViewById(R.id.product_thumb_item_description);
        TextView product_thumb_item_category = (TextView) v.findViewById(R.id.product_thumb_item_category);
        TextView product_thumb_item_value = (TextView) v.findViewById(R.id.product_thumb_item_value);
        ImageView product_thumb_item_picture = (ImageView) v.findViewById(R.id.product_thumb_item_picture);

        product_thumb_item_description.setText(model.getName());
        product_thumb_item_category.setText(model.getCategory());
        product_thumb_item_value.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getPrice()));
        Ion.with(product_thumb_item_picture)
                .fitCenter()
                .load(model.getPictureUrl());

        product_thumb_item_picture.setTag(model.getId());
        product_thumb_item_picture.setOnClickListener(new View.OnClickListener() {
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

                add(ProductModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }

    }

}
