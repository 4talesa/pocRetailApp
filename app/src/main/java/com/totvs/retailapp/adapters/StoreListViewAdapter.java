package com.totvs.retailapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.StoreModel;

import java.util.ArrayList;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class StoreListViewAdapter extends ListViewAdapterAbstract<StoreModel> {

    public StoreListViewAdapter(Context context, int layout, ArrayList<StoreModel> objects){
        super(context, layout, objects);
    }

    @Override
    protected void populateView(View v, StoreModel model) {

        TextView store_browse_item_store_name = (TextView) v.findViewById(R.id.store_browse_item_store_name);
        TextView store_browse_item_store_address = (TextView) v.findViewById(R.id.store_browse_item_store_address);
        TextView store_browse_item_store_distance = (TextView) v.findViewById(R.id.store_browse_item_store_distance);
        ImageView store_browse_item_thumb = (ImageView) v.findViewById(R.id.store_browse_item_thumb);

        store_browse_item_store_name.setText(model.getName());
        store_browse_item_store_address.setText(model.getAddress());
        store_browse_item_store_distance.setText(model.getDistance());
        Ion.with(store_browse_item_thumb)
                .fitCenter()
                .load(model.getUrlPicture());

    }
}
