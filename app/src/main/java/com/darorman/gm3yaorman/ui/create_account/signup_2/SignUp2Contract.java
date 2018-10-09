package com.darorman.gm3yaorman.ui.create_account.signup_2;

import com.darorman.gm3yaorman.api.model.SignUP.SignUpData1;
import com.darorman.gm3yaorman.api.model.spinners.city.CityData;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/12/2018.
 */
public interface SignUp2Contract {
    interface SignUp2View extends MvpView{
        void showProvince(List<String> provinceName, List<String> provinceID);
        void showOffice(List<String> officeName, List<String> officeID);
        void showCity(List<String> cityName, List<String> cityID);
        void navigateToSignUp3(String id);
    }

    interface SignUp2Presenter extends MvpPresenter<SignUp2View>{
        void getProvince();
        void getOffices();
        void getCities(CityData cityData);
        void creatAccount(SignUpData1 signUpData1);
    }
}
