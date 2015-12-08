package com.totvs.retailapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.facebook.appevents.AppEventsLogger;
import com.parse.Parse;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by rond.borges on 19/11/2015.
 */
public class AppRetailActivity extends AppCompatActivity {

    static final String defaultActivity         = "defaultActivity";
    static final String storeBrowseActivity     = "storeBrowseActivity";
    static final String productBrowseActivity   = "productBrowseActivity";
    static final String categorytBrowseActivity = "categorytBrowseActivity";
    static final String userProfileActivity     = "userProfileActivity";
    static final String rewardBalanceActivity   = "rewardBalanceActivity";
    static final String productDetailActivity   = "productDetailActivity";
    static final String purchaseDetailActivity  = "purchaseDetailActivity";
    static final String purchaseHistoryActivity  = "purchaseHistoryActivity";
    static final String purchaseHistoryAddActivity  = "purchaseHistoryAddActivity";

    protected String activityName = defaultActivity;

    private BeaconManager beaconManager;
    private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);
    private static final int REQUEST_ENABLE_BT = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        beaconManager = new BeaconManager(this);

        //Default values are 5s of scanning and 25s of waiting time to save CPU cycles.
        beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(30), 0);

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

                    System.out.println("onQueryTextSubmit: " + query);
                    // perform query here
                    return true;

                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    System.out.println("onQueryTextChange: " + newText);
                    return false;

                }

            });
        }catch (Exception e){
            System.out.println("Error - onCreateOptionsMenu - searchItem: " + e.toString());
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent it;

        System.out.println("activity: " + this.activityName);

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

            case R.id.menu_walk_in_reward_notify:
                it = new Intent(this, WalkInRewardNotifyActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_scan_product_reward_notify:
                it = new Intent(this, ScanProductRewardNotifyActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_product_detail:
                it = new Intent(this, ProductDetailActivity.class);
                this.startActivity(it);
                return true;

            case R.id.menu_purchase_reward_notify:
                it = new Intent(this, PurchaseRewardNotifyActivity.class);
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

    private void connectToService() {

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {

                // Start to discovery beacons in region
                try {
                    beaconManager.startRanging(ALL_ESTIMOTE_BEACONS_REGION);
                } catch (Exception e) {
                    Log.e("PushNotification", "Cannot start ranging", e);
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
            Log.d("Main Activity", "Error while stopping ranging", e);
        }
    }

    private void updateBeaconFound(Beacon beacon){
        Log.d("Main Activity", "Beacon found: " + beacon.getMacAddress().toString());
    }
}
