package com.darorman.gm3yaorman.ui.create_account;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.SignUP.SignUpData1;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.base.MvpView;
import com.darorman.gm3yaorman.ui.create_account.signup_2.SignUp2Fragment;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseFragment implements MvpView{

    @BindView(R.id.name)
    TextView name_txt;
    @BindView(R.id.mobile)
    TextView mobile_txt;
    @BindView(R.id.phone)
    TextView phone_txt;
    String name, mobile, phone;
    private Bundle bundle;
    private SignUp2Fragment signUp2Fragment;

    @Override
    protected void initializeDagger() {

    }

    @Override
    protected void intializePresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign_up;
    }

    @Override
    protected void initialization() {
        bundle = new Bundle();
        signUp2Fragment = new SignUp2Fragment();
        progressDialog = new ProgressDialog(getActivity());

        if(getActivity().getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    @OnClick(R.id.next)
    void navigateToSignUp2(View view){
        name = name_txt.getText().toString();
        phone = phone_txt.getText().toString();
        mobile = mobile_txt.getText().toString();
        if (validate()) {
            SignUpData1 signUpData1 = new SignUpData1(name, "", "",
                    "", mobile, phone, "");
            bundle.putParcelable("signup_data", signUpData1);
            signUp2Fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, signUp2Fragment).commit();
        }
    }

    private boolean validate() {
        if (name.isEmpty()){
            name_txt.setError("من فضلك ادخل الاسم");
            return false;
        }
        else if (mobile.isEmpty()){
            mobile_txt.setError("من فضلك ادخل رقم الموبايل");
            return false;
        }
        else if(mobile.length() != 11){
            mobile_txt.setError("من فضلك تاكد من ان الموبايل 11 رقم");
            return false;
        }
        return true;
    }

    @Override
    public void showError(String message) {

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
