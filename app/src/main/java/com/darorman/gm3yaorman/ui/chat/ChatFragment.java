package com.darorman.gm3yaorman.ui.chat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.chat.GetChatModel;
import com.darorman.gm3yaorman.api.model.chat.GetChatResult;
import com.darorman.gm3yaorman.api.model.chat.MessageData;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.usecase.Usecase;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends BaseFragment implements ChatContractor.ChatView {

    @Inject
    Usecase usecase;
    @BindView(R.id.chat_recycler)
    RecyclerView chatRecycler;
    @BindView(R.id.message)
    EditText message;
    private Bundle bundle;

    private ChatPresenter presenter;
    private RecyclerView.LayoutManager chatManager;
    private GetChatModel chatModel;
    private ChatAdapter chatAdapter;
    private MessageData messageData;
    private String userType , userId;
    private SharedPrefsHelper prefsHelper;

    @Override
    protected void initializeDagger() {
        ((App) getActivity().getApplicationContext()).getNetComponent().chatInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new ChatPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
        presenter.getChatMessages(chatModel);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initialization() {
        prefsHelper = new SharedPrefsHelper(getActivity());

        if (prefsHelper.getAdminID() != null)
            userType = "1";
        else if (prefsHelper.getUserID() != null)
            userType = "0";

        bundle = getArguments();
        if (bundle!= null){
            userId = bundle.getString("user_id");
        }
        chatModel = new GetChatModel(userId);
        chatManager = new LinearLayoutManager(getActivity());
        chatRecycler.setLayoutManager(chatManager);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void showChat(List<GetChatResult> messages) {
        chatAdapter = new ChatAdapter(messages);
        chatRecycler.setAdapter(chatAdapter);
        if (messages.size() > 1)
             chatRecycler.smoothScrollToPosition(messages.size()-1);
    }

    @Override
    public void sendMessage() {
        presenter.getChatMessages(chatModel);
    }

    @OnClick(R.id.send)
    void send(View view){
        if(validate()){
            messageData = new MessageData(userId, message.getText().toString(), userType);
            presenter.sendChatMessages(messageData);
            message.setText("");
        }
    }

    private boolean validate() {
        if (message.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "لا توجد رسالة", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
