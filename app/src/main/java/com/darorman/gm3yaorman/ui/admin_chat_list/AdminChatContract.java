package com.darorman.gm3yaorman.ui.admin_chat_list;

import com.darorman.gm3yaorman.api.model.admin_chat.AdminChatData;
import com.darorman.gm3yaorman.api.model.admin_chat.GetListChatResult;
import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/30/2018.
 */
public interface AdminChatContract {
    interface AdminChatView extends MvpView{
        void showChatList(List<GetListChatResult> chatData);
    }

    interface AdminChatPresenter extends MvpPresenter<AdminChatView>{
        void getChatList(AdminChatData chatData);
    }
}
