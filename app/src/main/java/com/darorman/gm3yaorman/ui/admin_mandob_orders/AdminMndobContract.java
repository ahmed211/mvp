package com.darorman.gm3yaorman.ui.admin_mandob_orders;

import com.darorman.gm3yaorman.api.model.admin_mndob.MndobData;
import com.darorman.gm3yaorman.api.model.admin_mndob.TransactionDataResult;
import com.darorman.gm3yaorman.api.model.admin_mndob.UpdateOrderData;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/18/2018.
 */
public interface AdminMndobContract {
    interface AdminMndobView extends MvpView{
        void showMndobData(List<TransactionDataResult> transactionDataResult);
        void updateList(int position);
    }

    interface AdminMndobPresenter extends MvpPresenter<AdminMndobView>{
        void getMndobOrders(MndobData mndobData);
        void updateOrder(UpdateOrderData updateOrderData, int position);
    }
}
