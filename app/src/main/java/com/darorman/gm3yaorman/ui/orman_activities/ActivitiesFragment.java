package com.darorman.gm3yaorman.ui.orman_activities;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.orman_activities.Activities;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.usecase.Usecase;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends BaseFragment implements ActivitiesContract.ActivitiesView {

    @Inject
    Usecase usecase;
    ActivitiesPresenter presenter;
    @BindView(R.id.activities_recycler)
    RecyclerView activitiesRecycler;
    private RecyclerView.LayoutManager activitiesMangaer;
    private OrmanActivitiesAdapter activitiesAdapter;

    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initializeDagger() {
        ((App) getContext().getApplicationContext()).getNetComponent().activitiesInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new ActivitiesPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
        presenter.getOrmanActivities();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activities;
    }

    @Override
    protected void initialization() {
        activitiesMangaer = new LinearLayoutManager(getActivity());
        activitiesRecycler.setLayoutManager(activitiesMangaer);
        progressDialog = new ProgressDialog(getActivity());
    }

    @Override
    public void showActivities(List<Activities> activities) {
        activitiesAdapter = new OrmanActivitiesAdapter(activities);
        activitiesRecycler.setAdapter(activitiesAdapter);

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

    @Override
    public void navigateToDetailsScreen() {

    }
}
