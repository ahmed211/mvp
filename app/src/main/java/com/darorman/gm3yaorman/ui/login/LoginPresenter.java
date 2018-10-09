package com.darorman.gm3yaorman.ui.login;

import com.darorman.gm3yaorman.api.model.Pojo;
import com.darorman.gm3yaorman.api.model.SignUP.SignUpData2;
import com.darorman.gm3yaorman.api.model.SignUP.signup_result.PojoSignup1;
import com.darorman.gm3yaorman.api.model.login.user_login.UserData;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.ui.create_account.signup_3.SignUp3Contract;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/13/2018.
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter{

    Scheduler mainScheduler, ioScheduler;
    Usecase usecase;

    public LoginPresenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void login(UserData userData) {
        checkViewAttached();
        addDisposal(usecase.login(userData)
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<Pojo>() {
            @Override
            public void onNext(Pojo pojo) {
                if (pojo.getLoginResult().getId().equals("0"))
                    getView().showError(pojo.getLoginResult().getMessage());
                else
                    getView().navigateToActivities(pojo.getLoginResult().getId());
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
