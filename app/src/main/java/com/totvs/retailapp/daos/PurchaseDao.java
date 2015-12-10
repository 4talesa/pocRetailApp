package com.totvs.retailapp.daos;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.models.PurchaseModel;
import com.totvs.retailapp.views.AppRetailViewAbsctrat;

import java.util.List;

/**
 * Created by rond.borges on 10/12/2015.
 */
public class PurchaseDao extends AppRetailDaoAbstract<PurchaseModel> {

    public PurchaseDao(Context context, AppRetailViewAbsctrat view) {
        super(context, view);
    }

    @Override
    public void save(PurchaseModel model) {

    }

    @Override
    public void delete(PurchaseModel model) {

    }

    @Override
    public List<PurchaseModel> list() {
        return null;
    }

    @Override
    public void find(String id) {

    }

    @Override
    public void get(String id) {
        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        url = "http://protected-bayou-8222.herokuapp.com/api/v1/"+ PurchaseModel.PURCHASE+"/"+id;
        HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.GET, url, null, view.getListener(), view.getErrorListener());

        requestQueue.add(jsArrRequest);
    }
}
