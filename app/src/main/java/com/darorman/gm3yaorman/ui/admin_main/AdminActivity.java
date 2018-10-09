package com.darorman.gm3yaorman.ui.admin_main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.base.BaseActivity;
import com.darorman.gm3yaorman.ui.admin_chat_list.AdminChatFragment;
import com.darorman.gm3yaorman.ui.admin_home.AdminHomeFragment;
import com.darorman.gm3yaorman.ui.admin_mandob_orders.AdminMndobOrdersFragment;
import com.darorman.gm3yaorman.ui.chat.ChatFragment;
import com.darorman.gm3yaorman.ui.home.HomeActivity;
import com.darorman.gm3yaorman.ui.orman_activities.ActivitiesFragment;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class AdminActivity extends BaseActivity {

    @BindView(R.id.screenName)
    TextView screenName;
    private SharedPrefsHelper prefsHelper;

    @Override
    protected int getContentView() {
        return R.layout.activity_admin;
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void initializeDagger() {

    }

    @Override
    protected void initialization() {
        prefsHelper = new SharedPrefsHelper(this);
        startFragment(new AdminHomeFragment(), "الرئيسية");
    }

    private void startFragment(Fragment fragment, String title) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.admin_layouts, fragment).commit();
        screenName.setText(title);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.admin_layouts);
            if(fragment instanceof ChatFragment)
                startFragment(new AdminChatFragment(), "قائمة المحادثات");
            else if (fragment instanceof AdminChatFragment || fragment instanceof AdminMndobOrdersFragment)
                startFragment(new AdminHomeFragment(), "الرئيسية");
            return true;
        }


        return super.onKeyDown(keyCode, event);
    }

    public void updateTileName(String title){
        screenName.setText(title);
    }

    @OnClick(R.id.logout)
    void logOut(View view){
        prefsHelper.putAdminID(null);
        prefsHelper.putUserID(null);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
