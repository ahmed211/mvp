package com.darorman.gm3yaorman.ui.chat;

import com.darorman.gm3yaorman.api.model.chat.GetChatModel;
import com.darorman.gm3yaorman.api.model.chat.GetChatResult;
import com.darorman.gm3yaorman.api.model.chat.MessageData;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 10/3/2018.
 */
public interface ChatContractor {
    interface ChatView extends MvpView{
        void showChat(List<GetChatResult> messages);
        void sendMessage();
    }

    interface ChatPresenter extends MvpPresenter<ChatView> {
        void getChatMessages(GetChatModel getChatModel);
        void sendChatMessages(MessageData messageData);
    }
}
