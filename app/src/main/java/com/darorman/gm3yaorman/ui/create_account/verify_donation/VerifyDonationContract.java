package com.darorman.gm3yaorman.ui.create_account.verify_donation;

import com.darorman.gm3yaorman.api.model.verify_donation.DoantionData;
import com.darorman.gm3yaorman.api.model.verify_mobile.MobileData;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

/**
 * Created by Ahmed Mostafa on 9/23/2018.
 */
public interface VerifyDonationContract {
    interface VerifyDonationView extends MvpView{
        void navigateToSignUP(String message);
    }

    interface VerifyDonationPresenter extends MvpPresenter<VerifyDonationView>{
        void checkDonation(DoantionData doantionData);
    }
}
