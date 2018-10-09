package com.darorman.gm3yaorman.ui.orman_branches;

import com.darorman.gm3yaorman.api.model.orman_branches.GetDepInfoResult;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;
import com.darorman.gm3yaorman.base.listeners.RecyclerIemLisener;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/1/2018.
 */
public interface BranchesContract {

    interface BranchesView extends MvpView{
        void showBranches(List<GetDepInfoResult> getDepInfoResult);
        void showDetailsDialog(GetDepInfoResult result);
    }

    interface BranchesPresenter extends MvpPresenter<BranchesView>{
        void getOrmanBranches();
        RecyclerIemLisener getRecyclerItemLisener();
    }
}
