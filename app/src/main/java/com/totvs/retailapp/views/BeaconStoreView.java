package com.totvs.retailapp.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.totvs.retailapp.AppRetailActivity;
import com.totvs.retailapp.WalkInRewardNotifyActivity;
import com.totvs.retailapp.daos.StoreDao;
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
    protected void populateView(BeaconStoreModel model) {

        try {
            Log.d("BeaconStoreView", "BeaconStoreModel Near Beacon found Id: "+model.getId()+", idStore: "+model.getIdStore());

            Intent it = new Intent(context, WalkInRewardNotifyActivity.class);

            it.putExtra(StoreModel.STORE_ID, model.getIdStore());
            StoreModel.getInstance().setId(model.getIdStore());
            StoreModel.getInstance().setName(model.getNameStore());
            StoreModel.getInstance().setAddress(model.getAddressStore());
            StoreModel.getInstance().setPictureUrl(model.getPictureUrlStore());

            try {
                StoreView storeView = new StoreView(context, ((AppRetailActivity) context).getWindow().getDecorView().getRootView());
                StoreDao storeDao = new StoreDao(context, storeView);
                storeDao.get(StoreModel.getInstance().getId());
                Log.d("StoreListViewAdapter", "storeDao started");
            }catch (Exception e){
                Log.d("StoreListViewAdapter", "Error", e);
            }

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
