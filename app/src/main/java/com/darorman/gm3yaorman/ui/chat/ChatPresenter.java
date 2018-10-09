package com.darorman.gm3yaorman.ui.chat;

import com.darorman.gm3yaorman.api.model.Pojo;
import com.darorman.gm3yaorman.api.model.chat.GetChatModel;
import com.darorman.gm3yaorman.api.model.chat.MessageData;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 10/3/2018.
 */
public class ChatPresenter extends BasePresenter<ChatContractor.ChatView> implements ChatContractor.ChatPresenter {

    private Scheduler maninScheduler, ioScheduler;
    private Usecase usecase;

    public ChatPresenter(Scheduler maninScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.maninScheduler = maninScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void getChatMessages(GetChatModel getChatModel) {
        checkViewAttached();
        addDisposal(usecase.getChatMessages(getChatModel)
                .subscribeOn(ioScheduler)
                .observeOn(maninScheduler)
                .subscribeWith(new DisposableObserver<Pojo>() {
                    @Override
                    public void onNext(Pojo pojo) {
                       getView().showChat(pojo.getGetChatResult());
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
    public void sendChatMessages(MessageData messageData) {
        checkViewAttached();
        addDisposal(usecase.sendChatMessages(messageData)
        .subscribeOn(ioScheduler)
        .observeOn(maninScheduler)
        .subscribeWith(new DisposableObserver<Pojo>() {
            @Override
            public void onNext(Pojo pojo) {
                if (pojo.getChatMessageResult().getId().equals("0"))
                    getView().showError(pojo.getChatMessageResult().getMessage());
                else
                    getView().sendMessage();

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
