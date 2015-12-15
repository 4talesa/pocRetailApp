package com.totvs.retailapp.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.totvs.retailapp.WalkInRewardNotifyActivity;
import com.totvs.retailapp.models.BeaconStoreModel;
import com.totvs.retailapp.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rond.borges on 11/12/2015.
 */
public class BeaconStoreView extends AppRetailViewAbsctrat<BeaconStoreModel> {

    public BeaconStoreView(Context context, View view) {
        super(context, view);
    }

    @Override
    public void populateView(BeaconStoreModel model) {
        //Toast.makeText(context, "BeaconStoreModel found Id: "+model.getId()+", idStore: "+model.getIdStore(), Toast.LENGTH_LONG).show();
        Log.d("BeaconStoreView", "BeaconStoreModel Near Beacon found Id: "+model.getId()+", idStore: "+model.getIdStore());
        try {
            Intent it = new Intent(context, WalkInRewardNotifyActivity.class);

            it.putExtra(StoreModel.STORE_ID, model.getIdStore());
            StoreModel.getInstance().setId(model.getIdStore());

            context.startActivity(it);
        }catch (Exception e){
            Log.d("BeaconStoreView", "Error:", e);
        }
    }

    @Override
    protected void updateJSONArray(JSONArray response) {
        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                populateView(BeaconStoreModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }
    }
}
