package com.totvs.retailapp.adapters;

import android.content.Context;
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
 * Created by rond.borges on 25/11/2015.
 */
public class ProductTwoWayViewAdapter extends TwoWayViewAdapterAbstract<ProductModel, ProductTwoWayViewAdapter.ViewHolder > {

    public ProductTwoWayViewAdapter(List<ProductModel> objects, int layout, Context context){
        super(objects, layout, context);
    }

    @Override
    public ProductTwoWayViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void populateView(ProductTwoWayViewAdapter.ViewHolder v, ProductModel model) {
        v.product_thumb_item_description.setText(model.getDescription());
        v.product_thumb_item_category.setText(model.getCategory());
        v.product_thumb_item_value.setText("R$ " + String.format("%1$,.2f", model.getValue()));
        Ion.with(v.product_thumb_item_picture)
                .fitCenter()
                .load(model.getUrlPicture());
    }

    public class ViewHolder extends TwoWayViewAdapterAbstract.ViewHolderAbstract{

        final TextView product_thumb_item_description;
        final TextView product_thumb_item_category;
        final TextView product_thumb_item_value;
        final ImageView product_thumb_item_picture;

        public ViewHolder(View v) {
            super(v);

            product_thumb_item_description = (TextView) itemView.findViewById(R.id.product_thumb_item_description);
            product_thumb_item_category = (TextView) itemView.findViewById(R.id.product_thumb_item_category);
            product_thumb_item_value = (TextView) itemView.findViewById(R.id.product_thumb_item_value);
            product_thumb_item_picture = (ImageView) itemView.findViewById(R.id.product_thumb_item_picture);
        }
    }
}

