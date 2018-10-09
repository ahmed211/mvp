package com.darorman.gm3yaorman.ui.banks_branches;

import com.darorman.gm3yaorman.api.model.banks.Bankes;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/5/2018.
 */
public interface BanksContract {
    interface BanksView extends MvpView{
        void showBanks(List<Bankes> bankes);
        void showDetailsDialog();
    }

    interface BanksPresenter extends MvpPresenter<BanksView>{
        void getBanksAccounts();
    }
}
