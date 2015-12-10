package com.totvs.retailapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.CategoryBrowseActivity;
import com.totvs.retailapp.R;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class StoreListViewAdapter extends ListViewAdapterAbstract<StoreModel> {

    public StoreListViewAdapter(Context context, int layout, ArrayList<StoreModel> objects){
        super(context, layout, objects, "Store");
    }

    @Override
    protected void populateView(View v, StoreModel model) {

        TextView store_browse_item_store_name = (TextView) v.findViewById(R.id.store_browse_item_store_name);
        TextView store_browse_item_store_address = (TextView) v.findViewById(R.id.store_browse_item_store_address);
        TextView store_browse_item_store_distance = (TextView) v.findViewById(R.id.store_browse_item_store_distance);
        ImageView store_browse_item_thumb = (ImageView) v.findViewById(R.id.store_browse_item_thumb);

        store_browse_item_store_name.setText(model.getName());
        store_browse_item_store_address.setText(model.getAddress());
        store_browse_item_store_distance.setText(model.getDistance());
        Ion.with(store_browse_item_thumb)
                .fitCenter()
                .load(model.getUrlPicture());

        store_browse_item_thumb.setTag(model.getId());
        store_browse_item_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), CategoryBrowseActivity.class);

                it.putExtra(StoreModel.STORE_ID, v.getTag().toString());

                v.getContext().startActivity(it);
            }
        });
    }

    @Override
    protected void updateJSONArray(JSONArray response){

        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                add(
                        new StoreModel(
                                object.getString("id")
                                , object.getString("name")
                                , object.getString("address")
                                , "1.5"
                                , object.getString("pictureurl")
                        )
                );

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }

    }

}
