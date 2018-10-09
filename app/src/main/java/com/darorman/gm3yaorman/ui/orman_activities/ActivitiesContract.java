package com.darorman.gm3yaorman.ui.orman_activities;

import com.darorman.gm3yaorman.api.model.orman_activities.Activities;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/3/2018.
 */
public interface ActivitiesContract {
    interface ActivitiesView extends MvpView{
        void showActivities(List<Activities> Activities);
        void navigateToDetailsScreen();
    }

    interface ActivitiesPresenter extends MvpPresenter<ActivitiesView>{
        void getOrmanActivities();
    }


}
