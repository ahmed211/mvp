package com.darorman.gm3yaorman.ui.orman_acitivities_details;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.orman_activities.Activities;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrmanActivitiesDetailsFragment extends BaseFragment implements ActivitiesDetailsContract.DetailsView {


    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    @BindView(R.id.activities_details)
    TextView activityDetails;
    @BindView(R.id.activities_title)
    TextView activityTitle;
    private Bundle bundle;
    private Activities activities;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    //private ArrayList<ImageModel> imageModelArrayList;
    private ArrayList<String> myImageList, sliderTitles;

    public OrmanActivitiesDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initializeDagger() {
        ((App) getContext().getApplicationContext()).getNetComponent().activitiesDetailsInject(this);

    }

    @Override
    protected void intializePresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_orman_activities_details;
    }

    @Override
    protected void initialization() {

        bundle = getArguments();
        if (bundle!= null){
            activities = bundle.getParcelable("details");
        }

        activityDetails.setText(activities.getActivityDec());
        activityTitle.setText(activities.getActivityTitel());
        pager.setAdapter(new SlidingImage_Adapter(getActivity(),activities.getGalleryAct()));
        progressDialog = new ProgressDialog(getActivity());

        indicator.setViewPager(pager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =activities.getGalleryAct().size();

        // Auto start of viewpager


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                try {
                    pager.setCurrentItem(currentPage++, true);
                }
                catch (Throwable e){}
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });


    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }
}
