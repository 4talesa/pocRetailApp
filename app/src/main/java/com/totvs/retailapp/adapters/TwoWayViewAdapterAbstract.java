package com.totvs.retailapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.daos.AppRetailDaoAbstract;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rond.borges on 24/11/2015.
 */
public abstract class TwoWayViewAdapterAbstract<T, VH extends TwoWayViewAdapterAbstract.ViewHolderAbstract> extends RecyclerView.Adapter<VH> {

    protected final Context context;
    protected int layout;
    protected List<T> objects;
    protected String[] urlFilters;
    protected String className;

    public static class ViewHolderAbstract extends RecyclerView.ViewHolder {

        public ViewHolderAbstract(View v) {
            super(v);
        }
    }

    public TwoWayViewAdapterAbstract(List<T> objects, int layout, Context context, String className) {
        this(objects, layout, context, className, null);
    }

    public TwoWayViewAdapterAbstract(List<T> objects, int layout, Context context, String className, String[] urlFilters) {
        this.objects    = objects;
        this.layout     = layout;
        this.context    = context;
        this.urlFilters = urlFilters;
        this.className  = className;

        searchData();
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void populateView(VH v, T model);

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        if (position >= 0 && position < objects.size()) {
            T model = objects.get(position);

            populateView(viewHolder, model);
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return objects.size();
    }

    public void clear() {
        objects.clear();
    }

    public void add(T model) {
        objects.add(model);
    }

    protected void searchData() {
        clear();

        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        url = AppRetailDaoAbstract.URL_API + className + "/" + getUrlFilters();

        HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.GET, url, null, createRequestJSONArraySuccessListener(), createRequestErrorListener());

        requestQueue.add(jsArrRequest);

    }

    public Response.Listener<JSONArray> createRequestJSONArraySuccessListener() {
        return new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                // TODO Auto-generated method stub
                Log.d("TwoWayViewAdapter", "Response.Listener<JSONArray> data: " + response.toString());
                Log.d("TwoWayViewAdapter", "Response.Listener<JSONArray> length: " + response.length());

                clear();
                updateJSONArray(response);
                notifyDataSetChanged();
            }
        };
    }

    protected abstract void updateJSONArray(JSONArray response);

    public Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.d("TwoWayViewAdapter", "Response.ErrorListener: ", error);
            }
        };
    }

    public String getUrlFilters() {
        if (urlFilters == null) {
            return "";
        } else {
            String newUrlFilter = "";
            for (int i = 0; i < urlFilters.length; i++) {
                if (urlFilters[i].length() > 0) {
                    if (newUrlFilter != "") {
                        newUrlFilter += "/";
                    }
                    newUrlFilter += urlFilters[i];
                }
            }
            return newUrlFilter;
        }
    }

    public void update() {
        searchData();
    }

}
