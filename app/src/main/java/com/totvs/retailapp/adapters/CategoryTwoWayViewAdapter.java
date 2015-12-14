package com.totvs.retailapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.totvs.retailapp.ProductBrowseActivity;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.CategoryModel;
import com.totvs.retailapp.models.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class CategoryTwoWayViewAdapter extends TwoWayViewAdapterAbstract<CategoryModel, CategoryTwoWayViewAdapter.ViewHolder > {

    public CategoryTwoWayViewAdapter(List<CategoryModel> objects, int layout, Context context){
        super(objects, layout, context, "Category");
    }

    public CategoryTwoWayViewAdapter(List<CategoryModel> objects, int layout, Context context, String[] urlFilters){
        super(objects, layout, context, "Category", urlFilters);
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

        v.textViewCategoryTitle.setText(model.getName());

        ProductTwoWayViewAdapter adapter = new ProductTwoWayViewAdapter(new ArrayList<ProductModel>(), R.layout.product_thumb_item, context, new String[]{CategoryModel.CATEGORY, model.getId(), "1"});
        v.twoWayViewBrowseCategoryList.setAdapter(adapter);

        v.textViewCategoryTitle.setTag(model.getId());
        v.textViewCategoryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), ProductBrowseActivity.class);

                it.putExtra(CategoryModel.CATEGORY_ID, v.getTag().toString());

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

    @Override
    protected void updateJSONArray(JSONArray response){

        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                add(CategoryModel.fromJson(object));

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }

    }

}
