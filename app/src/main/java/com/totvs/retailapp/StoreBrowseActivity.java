package com.totvs.retailapp;

import android.os.Bundle;
import android.widget.ListView;

import com.totvs.retailapp.adapters.StoreListViewAdapter;
import com.totvs.retailapp.models.StoreModel;

import java.util.ArrayList;

public class StoreBrowseActivity extends AppRetailActivity {

    private StoreListViewAdapter storeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_browse);

        this.activityName = storeBrowseActivity;

        ListView listView = (ListView) findViewById(R.id.listViewStore);

        ArrayList<StoreModel> arrayOfStores = new ArrayList<StoreModel>();
        storeAdapter = new StoreListViewAdapter(this, R.layout.store_browse_item, arrayOfStores);
        listView.setAdapter(storeAdapter);

        for (int i = 0; i<10; i++){
            arrayOfStores.add(new StoreModel(String.valueOf(i),"store "+i, "address "+i, "1.5", "http://lorempixel.com/325/175/food/Store/"));
        }
    }

}
