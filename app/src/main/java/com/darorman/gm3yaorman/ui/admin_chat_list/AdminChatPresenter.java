package com.darorman.gm3yaorman.ui.admin_chat_list;

import com.darorman.gm3yaorman.api.model.Pojo;
import com.darorman.gm3yaorman.api.model.admin_chat.AdminChatData;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/30/2018.
 */
public class AdminChatPresenter extends BasePresenter<AdminChatContract.AdminChatView> implements
        AdminChatContract.AdminChatPresenter{

    private Scheduler mainScheduler, ioScheduler;
    private Usecase usecase;

    public AdminChatPresenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void getChatList(AdminChatData chatData) {
        checkViewAttached();
        addDisposal(usecase.getAdminChat(chatData)
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<Pojo>() {
            @Override
            public void onNext(Pojo pojo) {
                getView().showChatList(pojo.getGetListChatResult());
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
