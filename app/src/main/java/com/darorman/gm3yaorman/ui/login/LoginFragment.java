package com.darorman.gm3yaorman.ui.login;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.SignUP.SignUpData2;
import com.darorman.gm3yaorman.api.model.login.user_login.UserData;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.ui.create_account.SignUpFragment;
import com.darorman.gm3yaorman.ui.create_account.signup_3.SignUp3Presenter;
import com.darorman.gm3yaorman.ui.create_account.verify_mobile.VerifyMobileFragment;
import com.darorman.gm3yaorman.ui.home.HomeActivity;
import com.darorman.gm3yaorman.ui.orman_activities.ActivitiesFragment;
import com.darorman.gm3yaorman.usecase.Usecase;
import com.darorman.gm3yaorman.utilities.AppUtils;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements LoginContract.LoginView {

    @Inject
    Usecase usecase;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    private LoginPresenter presenter;
    private SharedPrefsHelper prefsHelper;


    @Override
    protected void initializeDagger() {
        ((App) getContext().getApplicationContext()).getNetComponent().loginInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new LoginPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initialization() {
        progressDialog = new ProgressDialog(getActivity());
        prefsHelper = new SharedPrefsHelper(getActivity());

    }

    @Override
    public void navigateToActivities(String id) {
        prefsHelper.putUserID(id);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new ActivitiesFragment()).commit();
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


    @OnClick(R.id.login)
    void login(View view){
        if (validate()){
            UserData userData = new UserData(username.getText().toString(), password.getText().toString());
            presenter.login(userData);
            showDialog();
            AppUtils.hideKeyboard(getActivity());

        }
    }

    private boolean validate() {
        if (username.getText().toString().isEmpty()){
            username.setError("من فضلك ادخل اسم المستخدم");
            return false;
        }
        else if (password.getText().toString().isEmpty()){
            password.setError("من فضلك ادخل كلمة المرور");
            return false;
        }

        return true;
    }

    @OnClick(R.id.create_account)
    void createAccount(View view){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new VerifyMobileFragment()).commit();
        ((HomeActivity)this.getActivity()).updateTileName("انشاء حساب");
        AppUtils.hideKeyboard(getActivity());

    }


}
