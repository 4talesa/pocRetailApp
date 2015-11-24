package com.totvs.retailapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.StoreModel;

import java.util.ArrayList;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class StoreAdapter extends ArrayAdapter<StoreModel> {

    private int layout;

    public StoreAdapter(Context context, int layout, ArrayList<StoreModel> stores){
        super(context, 0, stores);
        this.layout = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        StoreModel store = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(this.layout, parent, false);
        }

        TextView store_browse_item_store_name       = (TextView) convertView.findViewById(R.id.store_browse_item_store_name);
        TextView store_browse_item_store_address    = (TextView) convertView.findViewById(R.id.store_browse_item_store_address);
        TextView store_browse_item_store_distance   = (TextView) convertView.findViewById(R.id.store_browse_item_store_distance);
        ImageView store_browse_item_thumb           = (ImageView) convertView.findViewById(R.id.store_browse_item_thumb);

        store_browse_item_store_name.setText(store.getName());
        store_browse_item_store_address.setText(store.getAddress());
        store_browse_item_store_distance.setText(store.getDistance());
        Ion.with(store_browse_item_thumb)
                .fitCenter()
                .load(store.getUrlPicture());

        return convertView;
    }
}
