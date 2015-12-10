package com.totvs.retailapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rond.borges on 24/11/2015.
 */
public abstract class ListViewAdapterAbstract<T> extends ArrayAdapter<T> {

    protected int layout;
    protected Context context;

    public ListViewAdapterAbstract(Context context, int layout, ArrayList<T> arrayList, String className){
        super(context, 0, arrayList);
        this.layout = layout;
        this.context = context;
        searchData(className);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        T model = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(this.layout, parent, false);
        }

        populateView(convertView, model);

        return convertView;
    }

    protected abstract void populateView(View v, T model);

    protected void searchData(String className){
        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        url = "http://protected-bayou-8222.herokuapp.com/api/v1/"+className+"/";
        HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.GET, url, null, createRequestJSONArraySuccessListener(), createRequestErrorListener());

        requestQueue.add(jsArrRequest);

    }

    public Response.Listener<JSONArray> createRequestJSONArraySuccessListener() {
        return new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                // TODO Auto-generated method stub
                Log.d("ListViewAdapter", "Response.Listener<JSONArray> data: " + response.toString());
                Log.d("ListViewAdapter", "Response.Listener<JSONArray> length: " + response.length());

                clear();
                updateJSONArray(response);
                notifyDataSetChanged();
            }
        };
    }

    protected abstract void updateJSONArray(JSONArray response);

    public Response.ErrorListener createRequestErrorListener(){
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.d("ListViewAdapter", "Response.ErrorListener: ", error);
            }
        };
    }

}
