package com.darorman.gm3yaorman.ui.create_account.signup_3;

import com.darorman.gm3yaorman.api.model.SignUP.SignUpData2;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

/**
 * Created by Ahmed Mostafa on 9/13/2018.
 */
public interface SignUp3Contract {
    interface SignUp3View extends MvpView{
        void navigateToActivities(String id);
    }

    interface SignUp3Presenter extends MvpPresenter<SignUp3View>{
        void creatAccount(SignUpData2 signUpData2);
    }
}
