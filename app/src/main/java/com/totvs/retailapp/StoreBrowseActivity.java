package com.totvs.retailapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.totvs.retailapp.adapters.StoreListViewAdapter;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.helpers.HelperJsonObjectRequest;
import com.totvs.retailapp.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StoreBrowseActivity extends AppRetailActivity {

    private StoreListViewAdapter storeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_browse);

        this.activityName = storeBrowseActivity;

        ListView listView = (ListView) findViewById(R.id.listViewStore);

        storeAdapter = new StoreListViewAdapter(this, R.layout.store_browse_item, new ArrayList<StoreModel>());
        listView.setAdapter(storeAdapter);

    }

}
