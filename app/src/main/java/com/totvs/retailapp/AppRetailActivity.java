package com.totvs.retailapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by rond.borges on 19/11/2015.
 */
public class AppRetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menu_user_profile) {
            Intent it = new Intent(this, UserProfileActivity.class);
            this.startActivity(it);
            return true;
        }

        if (id == R.id.menu_browse_store){
            Intent it = new Intent(this, StoreBrowseActivity.class);
            this.startActivity(it);
            return true;
        }

        if (id == R.id.menu_browse_category){
            Intent it = new Intent(this, CategoryBrowseActivity.class);
            this.startActivity(it);
            return true;
        }

        if (id == R.id.menu_browse_product){
            Intent it = new Intent(this, ProductBrowseActivity.class);
            this.startActivity(it);
            return true;
        }

        if (id == R.id.menu_walk_in_add){
            Intent it = new Intent(this, WalkInAddActivity.class);
            this.startActivity(it);
            return true;
        }

        if (id == R.id.menu_scan_product_add){
            Intent it = new Intent(this, ScanProductAddActivity.class);
            this.startActivity(it);
            return true;
        }

        if (id == R.id.menu_purchase_add){
            Intent it = new Intent(this, PurchaseAddActivity.class);
            this.startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
