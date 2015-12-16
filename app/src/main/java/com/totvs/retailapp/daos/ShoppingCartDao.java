package com.totvs.retailapp.daos;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.models.ShoppingCartModel;
import com.totvs.retailapp.views.AppRetailViewAbsctrat;

import java.util.List;

/**
 * Created by rond.borges on 16/12/2015.
 */
public class ShoppingCartDao extends AppRetailDaoAbstract<ShoppingCartModel> {

    public ShoppingCartDao(Context context, AppRetailViewAbsctrat view) {
        super(context, view);
    }

    @Override
    public void save(ShoppingCartModel model) {

    }

    @Override
    public void delete(ShoppingCartModel model) {

    }

    @Override
    public List<ShoppingCartModel> list() {
        return null;
    }

    @Override
    public void find(String id) {

    }

    @Override
    public void get(String id) {
        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        url = AppRetailDaoAbstract.URL_API+ ShoppingCartModel.SHOPPING_CART+"/"+id;
        HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.GET, url, null, view.getListener(), view.getErrorListener());

        requestQueue.add(jsArrRequest);
    }
}
