package com.totvs.retailapp.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.R;
import com.totvs.retailapp.models.RewardModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by rond.borges on 02/12/2015.
 */
public class RewardTwoWayViewAdapter extends TwoWayViewAdapterAbstract<RewardModel, RewardTwoWayViewAdapter.ViewHolder > {

    public RewardTwoWayViewAdapter(List<RewardModel> objects, int layout, Context context){
        super(objects, layout, context, "Reward");
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
    public void populateView(ViewHolder v, RewardModel model) {
        v.reward_balance_item_name.setText(model.getName());
        v.reward_balance_item_description.setText(model.getDescription());
        v.reward_balance_item_status.setText(model.getStatus());
        v.reward_balance_item_status.setEnabled(model.getStatus().toUpperCase().equals(context.getResources().getString(R.string.app_label_available).toUpperCase()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            v.reward_balance_item_status.setTextColor(model.getStatus().toUpperCase().equals(context.getResources().getString(R.string.app_label_available).toUpperCase()) ? context.getColor(R.color.colorLabelButtonReward) : context.getColor(R.color.colorLabelLight_Disabled));
            v.reward_balance_item_status.setBackgroundColor(model.getStatus().toUpperCase().equals(context.getResources().getString(R.string.app_label_available).toUpperCase()) ? context.getColor(R.color.colorBackgroundButtonReward) : context.getColor(R.color.colorBackgroundDisabled));
        }else{
            v.reward_balance_item_status.setTextColor(model.getStatus().toUpperCase().equals(context.getResources().getString(R.string.app_label_available).toUpperCase()) ? context.getResources().getColor(R.color.colorLabelButtonReward) : context.getResources().getColor(R.color.colorLabelLight_Disabled));
            v.reward_balance_item_status.setBackgroundColor(model.getStatus().toUpperCase().equals(context.getResources().getString(R.string.app_label_available).toUpperCase()) ? context.getResources().getColor(R.color.colorBackgroundButtonReward) : context.getResources().getColor(R.color.colorBackgroundDisabled));
        }
        v.reward_balance_item_amount.setText(String.format("%1$,.2f", model.getAmount()));
        Ion.with(v.reward_balance_item_picture)
                .fitCenter()
                .load(model.getUrlPicture());
    }

    public class ViewHolder extends TwoWayViewAdapterAbstract.ViewHolderAbstract{

        final TextView reward_balance_item_name;
        final TextView reward_balance_item_description;
        final Button reward_balance_item_status;
        final TextView reward_balance_item_amount;
        final ImageView reward_balance_item_picture;

        public ViewHolder(View v) {
            super(v);

            reward_balance_item_name = (TextView) itemView.findViewById(R.id.reward_balance_item_name);
            reward_balance_item_description = (TextView) itemView.findViewById(R.id.reward_balance_item_description);
            reward_balance_item_status = (Button) itemView.findViewById(R.id.reward_balance_item_status);
            reward_balance_item_amount = (TextView) itemView.findViewById(R.id.reward_balance_item_amount);
            reward_balance_item_picture = (ImageView) itemView.findViewById(R.id.reward_balance_item_picture);
        }
    }

    @Override
    protected void updateJSONArray(JSONArray response){

        for (int i = 0; i<response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);

                add(
                        new RewardModel(
                                object.getString("id")
                                , object.getString("name")
                                , object.getString("detail")
                                , (object.getDouble("amount") <= 10 ? context.getResources().getString(R.string.app_label_available) : context.getResources().getString(R.string.app_label_unavailable))
                                , object.getDouble("amount")
                                , object.getString("pictureurl")
                        )
                );

            } catch (JSONException e) {
                Log.d("updateJSONArray", "Response.Listener<JSONArray> error", e);
            }
        }

    }

}
