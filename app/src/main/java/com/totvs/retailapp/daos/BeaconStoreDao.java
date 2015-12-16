package com.totvs.retailapp.daos;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.models.BeaconStoreModel;
import com.totvs.retailapp.views.AppRetailViewAbsctrat;

import java.util.List;

/**
 * Created by rond.borges on 11/12/2015.
 */
public class BeaconStoreDao extends AppRetailDaoAbstract<BeaconStoreModel> {

    public BeaconStoreDao(Context context, AppRetailViewAbsctrat view) {
        super(context, view);
    }

    @Override
    public void save(BeaconStoreModel model) {

    }

    @Override
    public void delete(BeaconStoreModel model) {

    }

    @Override
    public List<BeaconStoreModel> list() {
        return null;
    }

    @Override
    public void find(String id) {

    }

    @Override
    public void get(String id) {
        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        url = AppRetailDaoAbstract.URL_API + BeaconStoreModel.BEACONSTORE+"/"+id;
        HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.GET, url, null, view.getListener(), view.getErrorListener());

        requestQueue.add(jsArrRequest);
    }
}
