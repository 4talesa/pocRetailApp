package com.totvs.retailapp.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.totvs.retailapp.AppRetailActivity;
import com.totvs.retailapp.ShoppingCartActivity;
import com.totvs.retailapp.models.ShoppingCartItemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rond.borges on 17/12/2015.
 */
public class ShoppingCartItemView extends AppRetailViewAbsctrat<ShoppingCartItemModel> {

    public ShoppingCartItemView(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void populateView(ShoppingCartItemModel model) {

        try{
            updateShoppingCart();

        }catch (Exception e){
            Log.d("ShoppingCartItemView", "Error:", e);
        }

    }

    @Override
    protected void updateJSONArray(JSONArray response) {
        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                populateView(ShoppingCartItemModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }
        updateShoppingCart();
    }

    protected void updateShoppingCart(){
        try{
            if (context.getClass() ==  ShoppingCartActivity.class){
                ((AppRetailActivity) context).updateView();

                Log.d("ShoppingCartItemView", "populateView");
            }else {
                Intent it = new Intent(context, ShoppingCartActivity.class);

                context.startActivity(it);
                Log.d("ShoppingCartItemView", "start new activity");
            }

        }catch (Exception e){
            Log.d("ShoppingCartItemView", "updateShoppingCart:", e);
        }
    }

}
