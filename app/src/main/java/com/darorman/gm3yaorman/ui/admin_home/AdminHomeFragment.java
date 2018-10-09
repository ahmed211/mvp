package com.darorman.gm3yaorman.ui.admin_home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.ui.admin_chat_list.AdminChatFragment;
import com.darorman.gm3yaorman.ui.admin_main.AdminActivity;
import com.darorman.gm3yaorman.ui.admin_mandob_orders.AdminMndobOrdersFragment;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminHomeFragment extends BaseFragment {


    @Override
    protected void initializeDagger() {

    }

    @Override
    protected void intializePresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_admin_home;
    }

    @Override
    protected void initialization() {

    }

    @OnClick(R.id.chat)
    void navigateToChat(View view){
        startFragment(new AdminChatFragment(), "قائمة المحادثات");
    }

    @OnClick(R.id.mandob)
    void navigateToMandob(View view){
        startFragment(new AdminMndobOrdersFragment(), "طلبات المندوبين");
    }

    private void startFragment(Fragment fragment, String title) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.admin_layouts, fragment).commit();
        ((AdminActivity)getActivity()).updateTileName(title);
    }
}
