package com.darorman.gm3yaorman.ui.create_account.signup_3;

import com.darorman.gm3yaorman.api.model.SignUP.SignUpData2;
import com.darorman.gm3yaorman.api.model.SignUP.signup_result.PojoSignup1;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/13/2018.
 */
public class SignUp3Presenter extends BasePresenter<SignUp3Contract.SignUp3View> implements SignUp3Contract.SignUp3Presenter{

    Scheduler mainScheduler, ioScheduler;
    Usecase usecase;

    public SignUp3Presenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void creatAccount(SignUpData2 signUpData2) {
        checkViewAttached();
        addDisposal(usecase.createAccount2(signUpData2)
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<PojoSignup1>() {
            @Override
            public void onNext(PojoSignup1 pojoSignup1) {
                if (pojoSignup1.getInsResult().getId().equals("0"))
                    getView().showError(pojoSignup1.getInsResult().getMessage());
                else
                    getView().navigateToActivities(pojoSignup1.getInsResult().getId());
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
