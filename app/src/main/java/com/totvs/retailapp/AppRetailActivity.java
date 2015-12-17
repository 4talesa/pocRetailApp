package com.totvs.retailapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.facebook.appevents.AppEventsLogger;
import com.parse.ParseUser;
import com.totvs.retailapp.daos.BeaconStoreDao;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.helpers.HelperJsonObjectRequest;
import com.totvs.retailapp.views.BeaconStoreView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by rond.borges on 19/11/2015.
 */
public class AppRetailActivity extends AppCompatActivity {

    static final String DEFAULTACTIVITY                 = "defaultActivity";
    static final String STOREBROWSEACTIVITY             = "storeBrowseActivity";
    static final String PRODUCTBROWSEACTIVITY           = "productBrowseActivity";
    static final String CATEGORYBROWSEACTIVITY          = "categoryBrowseActivity";
    static final String USERPROFILEACTIVITY             = "userProfileActivity";
    static final String REWARDBALANCEACTIVITY           = "rewardBalanceActivity";
    static final String PRODUCTDETAILACTIVITY           = "productDetailActivity";
    static final String PURCHASEDETAILACTIVITY          = "purchaseDetailActivity";
    static final String PURCHASEHISTORYACTIVITY         = "purchaseHistoryActivity";
    static final String PURCHASEHISTORYADDACTIVITY      = "purchaseHistoryAddActivity";
    static final String PURCHASESCANPRODUCTADDACTIVITY  = "purchaseScanProductAddActivity";
    static final String SHOPPINGCARTACTIVITY            = "ShoppingCartActivity";

    protected String activityName = DEFAULTACTIVITY;

    private BeaconManager beaconManager;
    private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);
    private static final int REQUEST_ENABLE_BT = 1234;
    private Beacon beaconSelected;

    protected ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        beaconManager = new BeaconManager(this);

        currentUser = ParseUser.getCurrentUser();

        //Default values are 5s of scanning and 25s of waiting time to save CPU cycles.
        beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(60), TimeUnit.SECONDS.toMillis(30));

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
        }else{
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }else{
                connectToService();
            }
        }

        try{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            System.out.println("Error - onCreate - setDisplayHomeAsUpEnabled: " + e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);

        try {
            MenuItem searchItem = menu.findItem(R.id.action_search);

            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {

                    Log.d(activityName, "onQueryTextSubmit: " + query);
                    // perform query here

                    String url = "";

                    RequestQueue requestQueue = Volley.newRequestQueue(AppRetailActivity.this);

                    url = "http://beta.json-generator.com/api/json/get/4k20SIgSe";
                    HelperJsonObjectRequest jsObjRequest = new HelperJsonObjectRequest(Request.Method.POST, url, null, AppRetailActivity.this.createRequestJSONObjectSuccessListener(), AppRetailActivity.this.createRequestErrorListener());

                    url = "http://beta.json-generator.com/api/json/get/NyZwmvxrx";
                    HelperJsonArrayRequest jsArrRequest = new HelperJsonArrayRequest(Request.Method.POST, url, null, AppRetailActivity.this.createRequestJSONArraySuccessListener(), AppRetailActivity.this.createRequestErrorListener());

                    //requestQueue.add(jsObjRequest);
                    requestQueue.add(jsArrRequest);
                    return true;

                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    Log.d(activityName, "onQueryTextChange: " + newText);
                    return false;

                }

            });
        }catch (Exception e){
            System.out.println("Error - onCreateOptionsMenu - searchItem: " + e.toString());
        }

        return super.onCreateOptionsMenu(menu);
    }

    public Response.Listener<JSONObject> createRequestJSONObjectSuccessListener() {
        return new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                Log.d(activityName, "Response.Listener<JSONObject> " + response.toString());
            }
        };
    }

    public Response.Listener<JSONArray> createRequestJSONArraySuccessListener() {
        return new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                // TODO Auto-generated method stub
                Log.d(activityName, "Response.Listener<JSONArray> " + response.toString());
            }
        };
    }


    public Response.ErrorListener createRequestErrorListener(){
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.d(activityName, "Response.ErrorListener: ", error);
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent it;

        Log.d(activityName, "onOptionsItemSelected");

        switch (item.getItemId()) {

            case android.R.id.home:
                it = new Intent(this, StoreBrowseActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_user_profile:
                it = new Intent(this, UserProfileActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_browse_store:
                it = new Intent(this, StoreBrowseActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_browse_category:
                it = new Intent(this, CategoryBrowseActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_browse_product:
                it = new Intent(this, ProductBrowseActivity.class);
                this.startActivity(it);
                return true;

            /*case R.id.menu_walk_in_reward_notify:
                it = new Intent(this, WalkInRewardNotifyActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_scan_product_reward_notify:
                it = new Intent(this, ScanProductRewardNotifyActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_purchase_reward_notify:
                it = new Intent(this, PurchaseRewardNotifyActivity.class);
                this.startActivity(it);
                return true;*/

            case R.id.menu_product_detail:
                it = new Intent(this, ProductDetailActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_purchase_browse:
                it = new Intent(this, PurchaseHistoryActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_purchase_detail:
                it = new Intent(this, PurchaseDetailActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_purchase_history_add:
                it = new Intent(this, PurchaseHistoryAddActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_reward_balance:
                it = new Intent(this, RewardBalanceActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_sign_out:
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                it = new Intent(this, SignInActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_sign_in:
                it = new Intent(this, SignInActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_scan_product_add:
                it = new Intent(this, ScanProductAddActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_shopping_cart:
                it = new Intent(this, ShoppingCartActivity.class);
                this.startActivity(it);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);

        connectToService();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        connectToService();
    }

    @Override
    protected void onStop(){
        super.onStop();

        // Should be invoked in #onStop.
        beaconManager.disconnect();
    }

    @Override
    protected void onDestroy(){
        // Destroy beacon manager
        beaconManager.disconnect();

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                connectToService();
            } else {
                Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void connectToService() {

        beaconSelected = null;

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {

                // Start to discovery beacons in region
                try {
                    beaconManager.startRanging(ALL_ESTIMOTE_BEACONS_REGION);
                } catch (Exception e) {
                    Log.e(activityName, "Cannot start ranging", e);
                }

                beaconManager.setRangingListener(new BeaconManager.RangingListener() {
                    @Override
                    public void onBeaconsDiscovered(Region region, final List<Beacon> rangedBeacons) {
                        // Run in background, or else will make the app crash
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Beacon foundBeacon = null;
                                for (Beacon rangedBeacon : rangedBeacons) {
                                    foundBeacon = rangedBeacon;
                                    updateBeaconFound(foundBeacon);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void stopBeaconRanging(){
        try {
            beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS_REGION);
        } catch (Exception e) {
            Log.d(activityName, "Error while stopping ranging", e);
        }
    }

    public Boolean newBeaconNear(Beacon beacon){
        if ((Utils.computeAccuracy(beacon) < 0.10)) {
            if (beaconSelected == null){
                beaconSelected = beacon;
                return true;
            }else {
                if (!beaconSelected.getMacAddress().equals(beacon.getMacAddress())){
                    beaconSelected = beacon;
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }
    }

    private void updateBeaconFound(Beacon beacon){
        if (newBeaconNear(beacon)) {
            Log.d(activityName, "Near Beacon found: " + beacon.getMacAddress().toString());

            BeaconStoreView beaconStoreView = new BeaconStoreView(this, this.getWindow().getDecorView().getRootView());
            BeaconStoreDao beaconStoreDao = new BeaconStoreDao(this, beaconStoreView);
            beaconStoreDao.get(beacon.getMacAddress().toString());

            //stopBeaconRanging();
        }
    }
}
