package com.darorman.gm3yaorman.ui.representative;

import com.darorman.gm3yaorman.api.model.representative.OrderData;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/17/2018.
 */
public interface RepresentativeContract {
    interface RepresentativeView extends MvpView{
        void showCurrency(List<String> currencyName, List<String> currencyID);
        void showDonationTypes(List<String> typeName, List<String> typeID);
        void showDonationGoal(List<String> goalName, List<String> goalID);
        void showOrderMandobResult(String message);

    }

    interface RepresentativePresenter extends MvpPresenter<RepresentativeView>{
        void getCurrency();
        void getDonationType();
        void getDonationGoal();
        void makeOrder(OrderData orderData);
    }
}
