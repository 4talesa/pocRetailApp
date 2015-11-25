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

import java.util.ArrayList;

/**
 * Created by rond.borges on 24/11/2015.
 */
public abstract class ListViewAdapterModel<T> extends ArrayAdapter<T> {

    private int layout;

    public ListViewAdapterModel(Context context, int layout, ArrayList<T> arrayList){
        super(context, 0, arrayList);
        this.layout = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        try {
            T model = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(this.layout, parent, false);
            }

            populateView(convertView, model);

            return convertView;
        }catch(Exception e){
            System.out.println("ListViewAdapterModel - getView - Error: " + e.toString());
            throw e;
        }
    }

    protected abstract void populateView(View v, T model);
}
