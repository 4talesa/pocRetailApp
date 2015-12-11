package com.totvs.retailapp.views;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rond.borges on 10/12/2015.
 */
public abstract class AppRetailViewAbsctrat<T> {

    protected final Context context;
    protected final View view;
    protected Response.Listener<JSONArray> listener;
    protected Response.ErrorListener errorListener;

    public AppRetailViewAbsctrat(Context context, View view){
        this.context    = context;
        this.view       = view;

        this.listener = new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                // TODO Auto-generated method stub
                Log.d("TwoWayViewAdapter", "Response.Listener<JSONArray> data: " + response.toString());
                Log.d("TwoWayViewAdapter", "Response.Listener<JSONArray> length: " + response.length());

                updateJSONArray(response);

            }
        };

        this.errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.d("TwoWayViewAdapter", "Response.ErrorListener: ", error);
            }
        };
    };

    public abstract void populateView(T model);

    public Response.Listener<JSONArray> getListener() {
        return listener;
    }

    protected abstract void updateJSONArray(JSONArray response);

    public Response.ErrorListener getErrorListener(){
        return errorListener;
    }

}