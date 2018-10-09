package com.darorman.gm3yaorman.ui.admin_login;

import com.darorman.gm3yaorman.api.model.admin_login.AdminData;
import com.darorman.gm3yaorman.api.model.login.user_login.UserData;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/13/2018.
 */
public interface AdminLoginContract {
    interface AdminLoginView extends MvpView{
        void showOffices(List<String> officeName, List<String> officeID);
        void navigateToAdminScreens(String id);
    }

    interface AdminLoginPresenter extends MvpPresenter<AdminLoginView>{
        void getOffices();
        void login(AdminData adminData);
    }
}
