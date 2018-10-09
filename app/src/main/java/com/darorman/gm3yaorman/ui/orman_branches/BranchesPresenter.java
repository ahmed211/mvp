package com.darorman.gm3yaorman.ui.orman_branches;

import com.darorman.gm3yaorman.api.model.orman_branches.PojoOrmanBranches;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.base.listeners.RecyclerIemLisener;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/1/2018.
 */
public class BranchesPresenter extends BasePresenter<BranchesContract.BranchesView> implements BranchesContract.BranchesPresenter {

    private Scheduler mainScheduler, ioScheduler;
    private Usecase usecase;
    private PojoOrmanBranches pojoOrmanBranches;

    public BranchesPresenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void getOrmanBranches() {
        checkViewAttached();
        addDisposal(usecase.getOrmanBranches()
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<PojoOrmanBranches>(){

            @Override
            public void onNext(PojoOrmanBranches pojoOrmanBranches) {
                BranchesPresenter.this.pojoOrmanBranches = pojoOrmanBranches;
                getView().showBranches(pojoOrmanBranches.getGetDepInfoResult());
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

    @Override
    public RecyclerIemLisener getRecyclerItemLisener() {
        return position -> getView().showDetailsDialog(pojoOrmanBranches.getGetDepInfoResult().get(position));
    }
}
