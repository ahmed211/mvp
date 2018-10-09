package com.darorman.gm3yaorman.ui.banks_branches;

import com.darorman.gm3yaorman.api.model.banks.PojoBanks;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/5/2018.
 */

public class BanksPresenter extends BasePresenter<BanksContract.BanksView> implements BanksContract.BanksPresenter {
    private Scheduler mainScheduler, ioScheduler;
    private Usecase usecase;
    private PojoBanks pojoBanks;

    public BanksPresenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void getBanksAccounts() {
        checkViewAttached();
        addDisposal(usecase.getBanksAccounts()
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<PojoBanks>() {
            @Override
            public void onNext(PojoBanks pojoBanks) {
                getView().showBanks(pojoBanks.getBankesResult().getBankes());
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