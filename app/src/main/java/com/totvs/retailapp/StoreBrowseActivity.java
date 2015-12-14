package com.totvs.retailapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.totvs.retailapp.adapters.StoreListViewAdapter;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.helpers.HelperJsonObjectRequest;
import com.totvs.retailapp.models.AppRetailModelAbstract;
import com.totvs.retailapp.models.StoreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StoreBrowseActivity extends AppRetailActivity {

    private StoreListViewAdapter storeAdapter;
    EditText editTextStoreBrowseZip;
    Button buttonStoreBrowseChangeZip;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_browse);

        this.activityName = storeBrowseActivity;

        editTextStoreBrowseZip = (EditText) findViewById(R.id.editTextStoreBrowseZip);
        buttonStoreBrowseChangeZip = (Button) findViewById(R.id.buttonStoreBrowseChangeZip);

        buttonStoreBrowseChangeZip.setOnClickListener(getOnChangeZipClick());

        editTextStoreBrowseZip.setOnKeyListener(getOnKeyListener());

        listView = (ListView) findViewById(R.id.listViewStore);

        searchStore();

    }

    protected View.OnKeyListener getOnKeyListener() {
        return new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    buttonStoreBrowseChangeZip.performClick();

                    return true;
                }
                return false;
            }
        };
    }

    protected View.OnClickListener getOnChangeZipClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectToService();
                searchStore();
            }
        };
    }

    protected void searchStore(){
        storeAdapter = new StoreListViewAdapter(StoreBrowseActivity.this, R.layout.store_browse_item, new ArrayList<StoreModel>(), StoreModel.POSTAL_CODE, editTextStoreBrowseZip.getText().toString());
        listView.setAdapter(storeAdapter);
    }

}
