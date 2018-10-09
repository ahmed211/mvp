package com.darorman.gm3yaorman.ui.create_account.verify_mobile;

import com.darorman.gm3yaorman.api.model.Pojo;
import com.darorman.gm3yaorman.api.model.verify_mobile.MobileData;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/23/2018.
 */
public class VerifyMobilePresenter extends BasePresenter<VerifyMobileContract.VerifyMobileView>
        implements VerifyMobileContract.VerifyMobilePresenter{

    Scheduler mainScheduler, ioScheduler;
    Usecase usecase;

    public VerifyMobilePresenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void getUsers(MobileData mobileData) {
        checkViewAttached();
        addDisposal(usecase.verifyMobileUsers(mobileData)
        .observeOn(mainScheduler)
        .subscribeOn(ioScheduler)
        .subscribeWith(new DisposableObserver<Pojo>() {
            @Override
            public void onNext(Pojo pojo) {
                if (pojo.getGetDATAResult().size() == 0)
                    getView().navigateSignUP();
                else
                    getView().showUsers(pojo.getGetDATAResult());
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
