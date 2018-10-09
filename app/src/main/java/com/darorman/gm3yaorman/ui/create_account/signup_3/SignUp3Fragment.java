package com.darorman.gm3yaorman.ui.create_account.signup_3;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.SignUP.SignUpData2;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.ui.orman_activities.ActivitiesFragment;
import com.darorman.gm3yaorman.usecase.Usecase;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp3Fragment extends BaseFragment implements SignUp3Contract.SignUp3View {

    @Inject
    Usecase usecase;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    private SignUp3Presenter presenter;
    private Bundle bundle;
    private String id;
    private SharedPrefsHelper prefsHelper;


    @Override
    protected void initializeDagger() {
        ((App) getContext().getApplicationContext()).getNetComponent().signUpInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new SignUp3Presenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign_up3;
    }

    @Override
    protected void initialization() {
        bundle = getArguments();
        if (bundle!= null)
            id = bundle.getString("user_id");
        progressDialog = new ProgressDialog(getActivity());
        prefsHelper = new SharedPrefsHelper(getActivity());

    }

    @Override
    public void navigateToActivities(String id) {
        prefsHelper.putUserID(id);
        prefsHelper.putAdminID(null);
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


    @OnClick(R.id.register)
    void createAccount(View view){
        if (validate()){
            SignUpData2 signUpData2 = new SignUpData2(username.getText().toString(), email.getText().toString(),
                    id, password.getText().toString());
            presenter.creatAccount(signUpData2);
            showDialog();
        }
    }

    private boolean validate() {
        if (email.getText().toString().isEmpty()){
            email.setError("من فضلك ادخل البريد الالكتروني");
            return false;
        }
        else if (username.getText().toString().isEmpty()){
            username.setError("من فضلك ادخل اسم المستخدم");
            return false;
        }
        else if (password.getText().toString().isEmpty()){
            password.setError("من فضلك ادخل كلمة المرور");
            return false;
        }

        return true;
    }

}
