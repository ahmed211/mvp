package com.darorman.gm3yaorman.ui.orman_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.orman_activities.Activities;
import com.darorman.gm3yaorman.ui.orman_acitivities_details.OrmanActivitiesDetailsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Mostafa on 9/2/2018.
 */
public class OrmanActivitiesAdapter extends RecyclerView.Adapter<OrmanActivitiesAdapter.ViewHolder> {

    private List<Activities> activities;

    public OrmanActivitiesAdapter(List<Activities> activities) {
        this.activities = activities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.orman_activities_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(activities.get(i), i);
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.activities_title)
        TextView activityTitle;
        @BindView(R.id.activities_image)
        ImageView activityImage;
        @BindView(R.id.activities_details)
        TextView activityDetails;
        private Context context;
        private Bundle bundle;
        private OrmanActivitiesDetailsFragment detailsFragment;
        private FragmentManager fragmentManager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            bundle = new Bundle();
            detailsFragment = new OrmanActivitiesDetailsFragment();
            fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        }

        public void bind(Activities activities,  int posotion){
            activityTitle.setText(activities.getActivityTitel());
            activityDetails.setText(activities.getActivityDec());
            Glide.with(context)
                    .load(activities.getActivityImgUrl())
                    .into(activityImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bundle.putParcelable("details", activities);
                    detailsFragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.frame, detailsFragment).commit();
                }
            });
          //  branchItem.setOnClickListener(view -> recyclerIemLisener.onItemSelected(posotion));
        }
    }
}
