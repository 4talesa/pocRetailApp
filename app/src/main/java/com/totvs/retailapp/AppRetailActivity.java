package com.totvs.retailapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.appevents.AppEventsLogger;
import com.parse.Parse;
import com.parse.ParseUser;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Parse.initialize(this, getResources().getString(R.string.app_parse_app_id), getResources().getString(R.string.app_parse_app_key));

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
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

}
