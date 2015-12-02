package com.totvs.retailapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.RewardModel;

import java.util.List;

/**
 * Created by rond.borges on 02/12/2015.
 */
public class RewardTwoWayViewAdapter extends TwoWayViewAdapterAbstract<RewardModel, RewardTwoWayViewAdapter.ViewHolder > {

    public RewardTwoWayViewAdapter(List<RewardModel> objects, int layout, Context context){
        super(objects, layout, context);
    }

    @Override
    public RewardTwoWayViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void populateView(RewardTwoWayViewAdapter.ViewHolder v, RewardModel model) {
        v.reward_balance_item_name.setText(model.getName());
        v.reward_balance_item_description.setText(model.getDescription());
        v.reward_balance_item_status.setText(model.getStatus());
        v.reward_balance_item_amount.setText(String.format("%1$,.2f", model.getAmount()));
        Ion.with(v.reward_balance_item_picture)
                .fitCenter()
                .load(model.getUrlPicture());
    }

    public class ViewHolder extends TwoWayViewAdapterAbstract.ViewHolderAbstract{

        final TextView reward_balance_item_name;
        final TextView reward_balance_item_description;
        final TextView reward_balance_item_status;
        final TextView reward_balance_item_amount;
        final ImageView reward_balance_item_picture;

        public ViewHolder(View v) {
            super(v);

            reward_balance_item_name = (TextView) itemView.findViewById(R.id.reward_balance_item_name);
            reward_balance_item_description = (TextView) itemView.findViewById(R.id.reward_balance_item_description);
            reward_balance_item_status = (TextView) itemView.findViewById(R.id.reward_balance_item_status);
            reward_balance_item_amount = (TextView) itemView.findViewById(R.id.reward_balance_item_amount);
            reward_balance_item_picture = (ImageView) itemView.findViewById(R.id.reward_balance_item_picture);
        }
    }

}
