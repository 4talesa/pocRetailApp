package com.totvs.retailapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.ProductModel;

import java.util.List;

/**
 * Created by rond.borges on 24/11/2015.
 */
public class TwoWayViewAdapterModel extends RecyclerView.Adapter<TwoWayViewAdapterModel.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView product_related_item_description;
        TextView product_related_item_category;
        TextView product_related_item_value;
        ImageView product_related_item_thumb;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            product_related_item_description = (TextView) itemView.findViewById(R.id.product_related_item_description);
            product_related_item_category = (TextView) itemView.findViewById(R.id.product_related_item_category);
            product_related_item_value = (TextView) itemView.findViewById(R.id.product_related_item_value);
            product_related_item_thumb = (ImageView) itemView.findViewById(R.id.product_related_item_thumb);
        }
    }

    // Store a member variable for the contacts
    private List<ProductModel> mProducts;

    // Pass in the contact array into the constructor
    public TwoWayViewAdapterModel(List<ProductModel> products) {
        mProducts = products;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TwoWayViewAdapterModel.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.product_related_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TwoWayViewAdapterModel.ViewHolder viewHolder, int position) {
        try {
            // Get the data model based on position
            ProductModel model = mProducts.get(position);

            TextView product_related_item_description = viewHolder.product_related_item_description;
            TextView product_related_item_category = viewHolder.product_related_item_category;
            TextView product_related_item_value = viewHolder.product_related_item_value;
            ImageView product_related_item_thumb = viewHolder.product_related_item_thumb;

            product_related_item_description.setText(model.getDescription());
            product_related_item_category.setText(model.getCategory());
            product_related_item_value.setText("$ " + String.format("%1$,.2f", model.getValue()));
            Ion.with(product_related_item_thumb)
                    .fitCenter()
                    .load(model.getUrlPicture());
        }catch (Exception e){
            System.out.println("TwoWayViewAdapterModel - onBindViewHolder - Error: " + e.toString());
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
