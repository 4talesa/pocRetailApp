package com.totvs.retailapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.PurchaseItemModel;

import java.util.ArrayList;

/**
 * Created by rond.borges on 25/11/2015.
 */
public class PurchaseItemListViewAdapter extends ListViewAdapterAbstract<PurchaseItemModel> {

    public PurchaseItemListViewAdapter(Context context, int layout, ArrayList<PurchaseItemModel> objects){
        super(context, layout, objects);
    }

    @Override
    protected void populateView(View v, PurchaseItemModel model) {

        ImageView purchase_detail_item_picture = (ImageView) v.findViewById(R.id.purchase_detail_item_picture);
        TextView purchase_detail_item_description = (TextView) v.findViewById(R.id.purchase_detail_item_description);
        TextView purchase_detail_item_category = (TextView) v.findViewById(R.id.purchase_detail_item_category);
        TextView purchase_detail_item_brand = (TextView) v.findViewById(R.id.purchase_detail_item_brand);
        TextView purchase_detail_item_unit_label = (TextView) v.findViewById(R.id.purchase_detail_item_unit_label);
        TextView purchase_detail_item_unit_value = (TextView) v.findViewById(R.id.purchase_detail_item_unit_value);
        TextView purchase_detail_item_quantity_label = (TextView) v.findViewById(R.id.purchase_detail_item_quantity_label);
        TextView purchase_detail_item_quantity_value = (TextView) v.findViewById(R.id.purchase_detail_item_quantity_value);
        TextView purchase_detail_item_total_amount_label = (TextView) v.findViewById(R.id.purchase_detail_item_total_amount_label);
        TextView purchase_detail_item_total_amount_value = (TextView) v.findViewById(R.id.purchase_detail_item_total_amount_value);

        purchase_detail_item_description.setText(model.getDescription());
        purchase_detail_item_category.setText(model.getCategory());
        purchase_detail_item_brand.setText(model.getBrand());
        purchase_detail_item_unit_label.setText(model.getUnit());
        purchase_detail_item_unit_value.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getUnitValue()));
        purchase_detail_item_quantity_label.setText("Quantity");
        purchase_detail_item_quantity_value.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getQuantity()));
        purchase_detail_item_total_amount_label.setText("Total amount");
        purchase_detail_item_total_amount_value.setText(v.getResources().getString(R.string.app_label_dollar)+" " + String.format("%1$,.2f", model.getAmount()));

        Ion.with(purchase_detail_item_picture)
                .fitCenter()
                .load(model.getUrlPicture());

    }

}
