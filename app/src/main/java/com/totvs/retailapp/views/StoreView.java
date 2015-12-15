package com.totvs.retailapp.views;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.totvs.retailapp.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rond.borges on 15/12/2015.
 */
public class StoreView extends AppRetailViewAbsctrat<StoreModel> {

    public StoreView(Context context, View view) {
        super(context, view);
    }

    @Override
    public void populateView(StoreModel model) {
        StoreModel.setStoreModel(model);
    }

    @Override
    protected void updateJSONArray(JSONArray response) {
        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                populateView(StoreModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }
    }
}
