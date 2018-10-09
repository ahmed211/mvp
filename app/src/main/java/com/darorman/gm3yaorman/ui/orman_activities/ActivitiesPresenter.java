package com.darorman.gm3yaorman.ui.orman_activities;

import com.darorman.gm3yaorman.api.model.orman_activities.PojoActivities;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/3/2018.
 */
public class ActivitiesPresenter extends BasePresenter<ActivitiesContract.ActivitiesView> implements ActivitiesContract.ActivitiesPresenter {

    private Scheduler mainScheduler, ioScheduler;
    private Usecase usecase;
    private PojoActivities PojoActivities;

    public ActivitiesPresenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void getOrmanActivities() {
        checkViewAttached();
        addDisposal(usecase.getOrmanActivities()
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<PojoActivities>(){
            @Override
            public void onNext(PojoActivities pojoActivities) {
                getView().showActivities(pojoActivities.getActivitiesResult().getActivities());
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        }));
    }



}
