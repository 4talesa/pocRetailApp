package com.totvs.retailapp.daos;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.models.ProductModel;
import com.totvs.retailapp.models.StoreModel;
import com.totvs.retailapp.views.ProductView;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by rond.borges on 10/12/2015.
 */
public class ProductDao extends AppRetailDaoAbstract<ProductModel> {

    public ProductDao(Context context, ProductView productView) {
        super(context, productView);
    }

    @Override
    public void save(ProductModel model) {

    }

    @Override
    public void delete(ProductModel model) {

    }

    @Override
    public List list() {
        return null;
    }

    @Override
    public void find(String id) {

    }

    @Override
    public void get(String id) {
        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        url = AppRetailDaoAbstract.URL_API+ProductModel.PRODUCT+"/"+id+"/"+ StoreModel.getInstance().getId();
        HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.GET, url, null, view.getListener(), view.getErrorListener());

        requestQueue.add(jsArrRequest);
    }

}
