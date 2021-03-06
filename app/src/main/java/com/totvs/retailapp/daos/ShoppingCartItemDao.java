package com.totvs.retailapp.daos;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.models.ShoppingCartItemModel;
import com.totvs.retailapp.views.AppRetailViewAbsctrat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by rond.borges on 17/12/2015.
 */
public class ShoppingCartItemDao extends AppRetailDaoAbstract<ShoppingCartItemModel> {

    public ShoppingCartItemDao(Context context, AppRetailViewAbsctrat view) {
        super(context, view);
    }

    @Override
    public void save(ShoppingCartItemModel model) {
        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        url = AppRetailDaoAbstract.URL_API+ ShoppingCartItemModel.SHOPPING_CART_ITEM;

        Map<String, String> params = new HashMap<>();

        params.put("id", model.getId());
        params.put("idShoppingCart", model.getIdShoppingCart());
        params.put("idProduct", model.getIdProduct());
        params.put("status", model.getStatus());
        params.put("amountRequested", model.getAmountPurchased().toString());
        params.put("amountPurchased", model.getAmountPurchased().toString());
        params.put("unitPrice", model.getUnitValue().toString());
        params.put("pictureUrl", model.getPictureUrl());
        params.put("totalItem", model.getTotalItem().toString());

        HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.PUT, url, params, view.getListener(), view.getErrorListener());

        requestQueue.add(jsArrRequest);

    }

    @Override
    public void delete(ShoppingCartItemModel model) {
        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        url = AppRetailDaoAbstract.URL_API+ ShoppingCartItemModel.SHOPPING_CART_ITEM+"/"+model.getId();

        HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.DELETE, url, null, view.getListener(), view.getErrorListener());

        requestQueue.add(jsArrRequest);
    }

    @Override
    public List<ShoppingCartItemModel> list() {
        return null;
    }

    @Override
    public void find(String id) {

    }

    @Override
    public void get(String id) {
        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        url = AppRetailDaoAbstract.URL_API+ ShoppingCartItemModel.SHOPPING_CART_ITEM+"/"+id;
        HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.GET, url, null, view.getListener(), view.getErrorListener());

        requestQueue.add(jsArrRequest);
    }

}
