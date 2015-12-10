package com.totvs.retailapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.ProductDetailActivity;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by rond.borges on 25/11/2015.
 */
public class ProductTwoWayViewAdapter extends TwoWayViewAdapterAbstract<ProductModel, ProductTwoWayViewAdapter.ViewHolder > {

    public ProductTwoWayViewAdapter(List<ProductModel> objects, int layout, Context context){
        super(objects, layout, context, "Product");
    }

    @Override
    public ProductTwoWayViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void populateView(ProductTwoWayViewAdapter.ViewHolder v, ProductModel model) {
        v.product_thumb_item_description.setText(model.getDescription());
        v.product_thumb_item_category.setText(model.getCategory());
        v.product_thumb_item_value.setText(context.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getValue()));
        Ion.with(v.product_thumb_item_picture)
                .fitCenter()
                .load(model.getUrlPicture());

        v.product_thumb_item_picture.setTag(model.getId());
        v.product_thumb_item_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), ProductDetailActivity.class);

                it.putExtra(ProductModel.PRODUCT_ID, v.getTag().toString());

                v.getContext().startActivity(it);
            }
        });
    }

    public class ViewHolder extends TwoWayViewAdapterAbstract.ViewHolderAbstract{

        final TextView product_thumb_item_description;
        final TextView product_thumb_item_category;
        final TextView product_thumb_item_value;
        final ImageView product_thumb_item_picture;

        public ViewHolder(View v) {
            super(v);

            product_thumb_item_description = (TextView) itemView.findViewById(R.id.product_thumb_item_description);
            product_thumb_item_category = (TextView) itemView.findViewById(R.id.product_thumb_item_category);
            product_thumb_item_value = (TextView) itemView.findViewById(R.id.product_thumb_item_value);
            product_thumb_item_picture = (ImageView) itemView.findViewById(R.id.product_thumb_item_picture);
        }
    }

    @Override
    protected void updateJSONArray(JSONArray response){

        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                add(
                        new ProductModel(
                                object.getString("id")
                                , object.getString("name")
                                , "category"
                                , object.getString("pictureurl")
                                , 1.99
                                , object.getString("name")
                                , "each"
                        )
                );

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }

    }

}

