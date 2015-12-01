package com.totvs.retailapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.ProductModel;

import java.util.ArrayList;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class ProductListViewAdapter extends ListViewAdapterAbstract<ProductModel> {

    public ProductListViewAdapter(Context context, int layout, ArrayList<ProductModel> arrayList) {
        super(context, layout, arrayList);
    }

    @Override
    protected void populateView(View v, ProductModel model) {

        TextView product_thumb_item_description = (TextView) v.findViewById(R.id.product_thumb_item_description);
        TextView product_thumb_item_category = (TextView) v.findViewById(R.id.product_thumb_item_category);
        TextView product_thumb_item_value = (TextView) v.findViewById(R.id.product_thumb_item_value);
        ImageView product_thumb_item_picture = (ImageView) v.findViewById(R.id.product_thumb_item_picture);

        product_thumb_item_description.setText(model.getDescription());
        product_thumb_item_category.setText(model.getCategory());
        product_thumb_item_value.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getValue()));
        Ion.with(product_thumb_item_picture)
                .fitCenter()
                .load(model.getUrlPicture());

    }
}
