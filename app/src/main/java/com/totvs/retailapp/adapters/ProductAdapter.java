package com.totvs.retailapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.ProductModel;
import com.totvs.retailapp.models.StoreModel;

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

    }
}
