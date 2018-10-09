package com.darorman.gm3yaorman.ui.home;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blikoon.qrcodescanner.QRActivity;
import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseActivity;
import com.darorman.gm3yaorman.ui.DonationFragment;
import com.darorman.gm3yaorman.ui.admin_login.AdminLoginFragment;
import com.darorman.gm3yaorman.ui.admin_main.AdminActivity;
import com.darorman.gm3yaorman.ui.banks_branches.BanksBranchesFragment;
import com.darorman.gm3yaorman.ui.chat.ChatFragment;
import com.darorman.gm3yaorman.ui.create_account.SignUpFragment;
import com.darorman.gm3yaorman.ui.create_account.signup_2.SignUp2Fragment;
import com.darorman.gm3yaorman.ui.create_account.verify_mobile.VerifyMobileFragment;
import com.darorman.gm3yaorman.ui.login.LoginFragment;
import com.darorman.gm3yaorman.ui.orman_acitivities_details.OrmanActivitiesDetailsFragment;
import com.darorman.gm3yaorman.ui.orman_activities.ActivitiesFragment;
import com.darorman.gm3yaorman.ui.orman_branches.OrmanBranchesFragment;
import com.darorman.gm3yaorman.ui.representative.RepresentativeFragment;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity  {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.screenName)
    TextView screenName;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.logout_layout)
    LinearLayout logout;
    @BindView(R.id.login_layout)
    LinearLayout login;
    private SharedPrefsHelper prefsHelper;
    private String user_id;
    private Bundle bundle;




    //    @BindView(R.id.container)
//    LinearLayout container;
    private ActionBarDrawerToggle toggle;


    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initializePresenter() {
    }

    @Override
    protected void initializeDagger() {
        ((App) getApplicationContext()).getNetComponent().inject(this);
        }

    @Override
    protected void initialization() {
        prefsHelper = new SharedPrefsHelper(this);
        prefsHelper.putAdminID(null);
        user_id = prefsHelper.getUserID();
        if (user_id == null)
            logout.setVisibility(View.GONE);
        else
            login.setVisibility(View.GONE);

        startFragment(new ActivitiesFragment(), getResources().getString(R.string.activities));
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                frame.setTranslationX(slideOffset * drawerView.getWidth()* -1);
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK ) {

            startFragment(new ActivitiesFragment(), getResources().getString(R.string.activities));
            return true;
        }


        return super.onKeyDown(keyCode, event);
    }


    @OnClick(R.id.menu)
    void drawerLayout(View view){
        if (drawerLayout.isDrawerOpen(Gravity.END)) {
            drawerLayout.closeDrawer(Gravity.END);

        } else {
            drawerLayout.openDrawer(Gravity.END);
        }

    }

    @OnClick(R.id.activities_layout)
    void openActivitiesFragment(View view){
        startFragment(new ActivitiesFragment(), getResources().getString(R.string.activities));
    }


    @OnClick(R.id.logout_layout)
    void logOut(View view){
        prefsHelper.putUserID(null);
        prefsHelper.putAdminID(null);
        startFragment(new ActivitiesFragment(), getResources().getString(R.string.activities));
        logout.setVisibility(View.GONE);
        login.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.orman_branches_layout)
    void openOrmanBranchesFragment(View view){
        startFragment(new OrmanBranchesFragment(), getResources().getString(R.string.branches));
    }

    @OnClick(R.id.banks_layout)
    void openBankesFragment(View view){
        startFragment(new BanksBranchesFragment(), getResources().getString(R.string.banks));
    }

    @OnClick(R.id.login_layout)
    void openLogin(View view){
        startFragment(new LoginFragment(), getResources().getString(R.string.login));
        logout.setVisibility(View.VISIBLE);
        login.setVisibility(View.GONE);

    }
    private void startFragment(Fragment fragment, String title) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragment).commit();
        screenName.setText(title);

        if (drawerLayout.isDrawerOpen(Gravity.END)) {
            drawerLayout.closeDrawer(Gravity.END);

        }
    }

    @OnClick(R.id.isal_layout)
    void openIsal(View view){
        Intent intent = new Intent(this, QRActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.donate)
    void openDonationSite(View view){
        user_id = prefsHelper.getUserID();
        if (user_id == null)
            startFragment(new LoginFragment(), getResources().getString(R.string.login));
        else
            startFragment(new DonationFragment(), getResources().getString(R.string.donation));
    }

    @OnClick(R.id.mndob_layout)
    void openMndob(View view){
        user_id = prefsHelper.getUserID();
        if (user_id == null)
            startFragment(new LoginFragment(), getResources().getString(R.string.login));
        else
            startFragment(new RepresentativeFragment(), getResources().getString(R.string.mndob));
    }

    @OnClick(R.id.chat_layout)
    void openChat(View view){
        user_id = prefsHelper.getUserID();
        if (user_id == null)
            startFragment(new LoginFragment(), getResources().getString(R.string.login));
        else {
            ChatFragment chatFragment;
            bundle = new Bundle();
            chatFragment = new ChatFragment();
            bundle.putString("user_id", user_id);
            chatFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, chatFragment).commit();

            if (drawerLayout.isDrawerOpen(Gravity.END)) {
                drawerLayout.closeDrawer(Gravity.END);

            }

        }

    }

    @OnClick(R.id.admin_layout)
    void openAdmin(View view){
        startFragment(new AdminLoginFragment(), "تسجيل دخول المدير");
    }

    public void updateTileName(String title){
        screenName.setText(title);
    }
}
