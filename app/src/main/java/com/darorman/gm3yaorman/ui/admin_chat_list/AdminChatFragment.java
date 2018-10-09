package com.darorman.gm3yaorman.ui.admin_chat_list;


import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.admin_chat.AdminChatData;
import com.darorman.gm3yaorman.api.model.admin_chat.GetListChatResult;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.usecase.Usecase;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminChatFragment extends BaseFragment implements AdminChatContract.AdminChatView {

    @Inject
    Usecase usecase;
    @BindView(R.id.admin_chat_recycler)
    RecyclerView chatRecycler;
    private RecyclerView.LayoutManager chatManager;
    private AdminChatAdapter chatAdapter;
    private AdminChatPresenter presenter;
    private AdminChatData chatData;
    private SharedPrefsHelper prefsHelper;

    @Override
    protected void initializeDagger() {
        ((App) getActivity().getApplicationContext()).getNetComponent().adminChatListInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new AdminChatPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
        presenter.getChatList(chatData);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_admin_chat;
    }

    @Override
    protected void initialization() {
        chatManager = new LinearLayoutManager(getActivity());
        chatRecycler.setLayoutManager(chatManager);
        progressDialog = new ProgressDialog(getActivity());
        prefsHelper = new SharedPrefsHelper(getActivity());
        chatData = new AdminChatData(/*prefsHelper.getAdminID()*/"46");
    }

    @Override
    public void showChatList(List<GetListChatResult> chatData) {
        chatAdapter = new AdminChatAdapter(chatData);
        chatRecycler.setAdapter(chatAdapter);
        dismissDialog();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        dismissDialog();
    }

    @Override
    public void showDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissDialog() {
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
