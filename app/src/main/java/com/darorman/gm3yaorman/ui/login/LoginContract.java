package com.darorman.gm3yaorman.ui.login;

import com.darorman.gm3yaorman.api.model.SignUP.SignUpData2;
import com.darorman.gm3yaorman.api.model.login.user_login.UserData;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

/**
 * Created by Ahmed Mostafa on 9/13/2018.
 */
public interface LoginContract {
    interface LoginView extends MvpView{
        void navigateToActivities(String id);
    }

    interface LoginPresenter extends MvpPresenter<LoginView>{
        void login(UserData userData);
    }
}
