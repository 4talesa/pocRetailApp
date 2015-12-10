package com.totvs.retailapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.CategoryBrowseActivity;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by rond.borges on 25/11/2015.
 */
public class StoreTwoWayViewAdapter extends TwoWayViewAdapterAbstract<StoreModel, StoreTwoWayViewAdapter.ViewHolder > {

    public StoreTwoWayViewAdapter(List<StoreModel> objects, int layout, Context context){
        super(objects, layout, context, "Store");
    }

    @Override
    public StoreTwoWayViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void populateView(StoreTwoWayViewAdapter.ViewHolder v, StoreModel model) {
        v.store_browse_item_store_name.setText(model.getName());
        v.store_browse_item_store_address.setText(model.getAddress());
        v.store_browse_item_store_distance.setText(model.getDistance());
        Ion.with(v.store_browse_item_thumb)
                .fitCenter()
                .load(model.getUrlPicture());

        v.store_browse_item_thumb.setTag(model.getId());
        v.store_browse_item_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), CategoryBrowseActivity.class);

                it.putExtra(StoreModel.STORE_ID, v.getTag().toString());

                v.getContext().startActivity(it);
            }
        });
    }

    public class ViewHolder extends TwoWayViewAdapterAbstract.ViewHolderAbstract{

        final TextView store_browse_item_store_name;
        final TextView store_browse_item_store_address;
        final TextView store_browse_item_store_distance;
        final ImageView store_browse_item_thumb;

        public ViewHolder(View v) {
            super(v);

            store_browse_item_store_name = (TextView) v.findViewById(R.id.store_browse_item_store_name);
            store_browse_item_store_address = (TextView) v.findViewById(R.id.store_browse_item_store_address);
            store_browse_item_store_distance = (TextView) v.findViewById(R.id.store_browse_item_store_distance);
            store_browse_item_thumb = (ImageView) v.findViewById(R.id.store_browse_item_thumb);
        }
    }

    @Override
    protected void updateJSONArray(JSONArray response){

        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                Log.d("updateJSONArray", "Response.Listener<JSONArray> object pos: " + i);
                Log.d("updateJSONArray", "Response.Listener<JSONArray> object data: " + object.toString());

                /*this.objects.add(
                        new ProductModel(
                                object.getString("_id")
                                , object.getString("name")
                                , object.getString("address")
                                , "1.5"
                                , object.getString("pictureurl")
                        ));*/
            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }

    }

}
