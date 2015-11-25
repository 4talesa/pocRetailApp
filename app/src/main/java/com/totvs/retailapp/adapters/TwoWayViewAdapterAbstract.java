package com.totvs.retailapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;

import java.util.List;

/**
 * Created by rond.borges on 24/11/2015.
 */
public abstract class TwoWayViewAdapterAbstract<T, VH extends TwoWayViewAdapterAbstract.ViewHolderAbstract> extends RecyclerView.Adapter<VH> {

    protected final Context context;
    protected int layout;
    protected List<T> objects;

    public static class ViewHolderAbstract extends RecyclerView.ViewHolder{

        public ViewHolderAbstract (View v){
            super(v);
        }
    }

    public TwoWayViewAdapterAbstract(List<T> objects, int layout, Context context) {
        this.objects    = objects;
        this.layout     = layout;
        this.context    = context;
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void populateView(VH v, T model);

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        if (position >= 0 && position < objects.size()) {
            T model = objects.get(position);

            populateView(viewHolder, model);
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return objects.size();
    }
}
