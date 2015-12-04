package com.totvs.retailapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.ProductBrowseActivity;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.CategoryModel;
import com.totvs.retailapp.models.ProductModel;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class CategoryTwoWayViewAdapter extends TwoWayViewAdapterAbstract<CategoryModel, CategoryTwoWayViewAdapter.ViewHolder > {

    public CategoryTwoWayViewAdapter(List<CategoryModel> objects, int layout, Context context){
        super(objects, layout, context);
    }

    @Override
    public CategoryTwoWayViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void populateView(CategoryTwoWayViewAdapter.ViewHolder v, CategoryModel model) {

        v.textViewCategoryTitle.setText(model.getDescription());

        List<ProductModel> products = new ArrayList<ProductModel>();

        for (int i = 1; i <= 4; i++) {
            products.add(
                    new ProductModel(
                            String.valueOf(i)
                            , context.getResources().getString(R.string.app_label_product)+" "+i
                            , String.valueOf(i)
                            , "http://lorempixel.com/175/175/food/Product/"
                            , 1.99
                            , context.getResources().getString(R.string.app_label_category)+" "+i
                            , context.getResources().getString(R.string.app_label_product_preview_unit)
                    ));
        }

        ProductTwoWayViewAdapter adapter = new ProductTwoWayViewAdapter(products, R.layout.product_thumb_item, context);
        v.twoWayViewBrowseCategoryList.setAdapter(adapter);

        v.textViewCategoryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), ProductBrowseActivity.class);
                v.getContext().startActivity(it);
            }
        });
    }

    public class ViewHolder extends TwoWayViewAdapterAbstract.ViewHolderAbstract{

        final TwoWayView twoWayViewBrowseCategoryList;
        final TextView textViewCategoryTitle;

        public ViewHolder(View v) {
            super(v);

            twoWayViewBrowseCategoryList = (TwoWayView) itemView.findViewById(R.id.twoWayViewBrowseCategoryList);
            textViewCategoryTitle = (TextView) itemView.findViewById(R.id.textViewCategoryTitle);
        }
    }
}
