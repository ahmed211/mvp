package com.darorman.gm3yaorman.ui.create_account.verify_donation;

import com.darorman.gm3yaorman.api.model.Pojo;
import com.darorman.gm3yaorman.api.model.verify_donation.DoantionData;
import com.darorman.gm3yaorman.api.model.verify_mobile.MobileData;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/24/2018.
 */
public class VerifyDonationPresenter extends BasePresenter<VerifyDonationContract.VerifyDonationView>
        implements VerifyDonationContract.VerifyDonationPresenter {

    Scheduler mainScheduler, ioScheduler;
    Usecase usecase;

    public VerifyDonationPresenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }
    @Override
    public void checkDonation(DoantionData doantionData) {
        checkViewAttached();
        addDisposal(usecase.checkDonation(doantionData)
                .observeOn(mainScheduler)
                .subscribeOn(ioScheduler)
                .subscribeWith(new DisposableObserver<Pojo>() {
                    @Override
                    public void onNext(Pojo pojo) {
                        if (pojo.getCheckResult().getDonation_money().equals("0"))
                            getView().showError(pojo.getCheckResult().getMessage());
                        else
                            getView().navigateToSignUP(pojo.getCheckResult().getMessage());

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
