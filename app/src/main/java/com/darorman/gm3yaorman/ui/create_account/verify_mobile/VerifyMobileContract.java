package com.darorman.gm3yaorman.ui.create_account.verify_mobile;

import com.darorman.gm3yaorman.api.model.verify_mobile.GetDATAResult;
import com.darorman.gm3yaorman.api.model.verify_mobile.MobileData;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/23/2018.
 */
public interface VerifyMobileContract {
    interface VerifyMobileView extends MvpView{
        void showUsers(List<GetDATAResult> getDATAResult);
        void navigateToVerifyDonation();
        void navigateSignUP();
    }

    interface VerifyMobilePresenter extends MvpPresenter<VerifyMobileView>{
        void getUsers(MobileData mobileData);
    }
}
