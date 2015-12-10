package com.totvs.retailapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.PurchaseDetailActivity;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.PurchaseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class PurchaseListViewAdapter extends ListViewAdapterAbstract<PurchaseModel> {

    public PurchaseListViewAdapter(Context context, int layout, ArrayList<PurchaseModel> objects){
        super(context, layout, objects, "Purchase");
    }

    @Override
    protected void populateView(View v, PurchaseModel model) {

        TextView purchase_history_date = (TextView) v.findViewById(R.id.purchase_history_date);
        TextView purchase_history_store_name = (TextView) v.findViewById(R.id.purchase_history_store_name);
        TextView purchase_history_store_address = (TextView) v.findViewById(R.id.purchase_history_store_address);
        TextView purchase_history_total_quantity = (TextView) v.findViewById(R.id.purchase_history_total_quantity);
        TextView purchase_history_total_amount = (TextView) v.findViewById(R.id.purchase_history_total_amount);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        purchase_history_date.setText(dateFormat.format(model.getDate()));
        purchase_history_store_name.setText(model.getStore());
        purchase_history_store_address.setText(model.getStoreAddress());
        purchase_history_total_quantity.setText(String.format("%1$,.2f", model.getTotalQuantity()));
        purchase_history_total_amount.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getTotalAmount()));

        v.setTag(model.getId());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), PurchaseDetailActivity.class);

                it.putExtra(PurchaseModel.PURCHASE_ID, v.getTag().toString());

                v.getContext().startActivity(it);
            }
        });

    }
    @Override
    protected void updateJSONArray(JSONArray response){

        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                add(
                        new PurchaseModel(
                                object.getString("id")
                                , new Date()
                                , object.getString("idstore")
                                , object.getString("iduser")
                                , context.getResources().getString(R.string.app_label_store)+" "+object.getString("idstore")
                                , context.getResources().getString(R.string.app_label_user)+" "+object.getString("iduser")
                                , context.getResources().getString(R.string.app_label_address)+" "+object.getString("idstore")
                                , 115.75
                                , 11.00
                        )
                );

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }

    }

}
