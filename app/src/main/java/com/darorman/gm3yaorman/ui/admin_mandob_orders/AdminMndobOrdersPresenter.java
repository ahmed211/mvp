package com.darorman.gm3yaorman.ui.admin_mandob_orders;

import com.darorman.gm3yaorman.api.model.Pojo;
import com.darorman.gm3yaorman.api.model.admin_mndob.MndobData;
import com.darorman.gm3yaorman.api.model.admin_mndob.UpdateOrderData;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/19/2018.
 */
public class AdminMndobOrdersPresenter extends BasePresenter<AdminMndobContract.AdminMndobView> implements AdminMndobContract.AdminMndobPresenter {

    private Scheduler maninScheduler, ioScheduler;
    private Usecase usecase;

    public AdminMndobOrdersPresenter(Scheduler maninScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.maninScheduler = maninScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void getMndobOrders(MndobData mndobData) {
        checkViewAttached();
        addDisposal(usecase.getMndobOrders(mndobData)
        .subscribeOn(ioScheduler)
        .observeOn(maninScheduler)
        .subscribeWith(new DisposableObserver<Pojo>() {
            @Override
            public void onNext(Pojo pojo) {
                getView().showMndobData(pojo.getTransactionDataResult());
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        }));
    }

    @Override
    public void updateOrder(UpdateOrderData updateOrderData, int position) {
        checkViewAttached();
        addDisposal(usecase.updateOrderData(updateOrderData)
                .subscribeOn(ioScheduler)
                .observeOn(maninScheduler)
                .subscribeWith(new DisposableObserver<Pojo>() {
                    @Override
                    public void onNext(Pojo pojo) {
//                        if (pojo.getUpdateTransactionResult().getId().equals("1"))
                            getView().updateList(position);
//                        else
//                            getView().showError(pojo.getUpdateTransactionResult().getMassege());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e.getMessage());
                      }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
