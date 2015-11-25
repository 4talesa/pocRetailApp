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
public class ProductAdapter extends AppRetailListAdapter<ProductModel> {

    public ProductAdapter(Context context, int layout, ArrayList<ProductModel> arrayList) {
        super(context, layout, arrayList);
    }

    @Override
    protected void populateView(View v, ProductModel model) {
        try {

            TextView product_browse_item_description = (TextView) v.findViewById(R.id.product_browse_item_description);
            TextView product_browse_item_category = (TextView) v.findViewById(R.id.product_browse_item_category);
            TextView product_browse_item_value = (TextView) v.findViewById(R.id.product_browse_item_value);
            ImageView product_browse_item_thumb = (ImageView) v.findViewById(R.id.product_browse_item_thumb);

            product_browse_item_description.setText(model.getDescription());
            product_browse_item_category.setText(model.getCategory());
            product_browse_item_value.setText("$ " + String.format("%1$,.2f", model.getValue()));
            Ion.with(product_browse_item_thumb)
                    .fitCenter()
                    .load(model.getUrlPicture());
        }catch (Exception e){
            System.out.println("ProductAdapter - populateView - Error: " + e.toString());
        }
    }
}
