package com.darorman.gm3yaorman.ui.create_account.verify_donation;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.verify_donation.DoantionData;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.ui.create_account.signup_3.SignUp3Fragment;
import com.darorman.gm3yaorman.usecase.Usecase;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyDonationFragment extends BaseFragment implements VerifyDonationContract.VerifyDonationView{

    @Inject
    Usecase usecase;
    @BindView(R.id.donation_value)
    EditText donationValue;
    private VerifyDonationPresenter presenter;
    private Bundle bundle, sign3bundle;
    private SignUp3Fragment signUp3Fragment;
    private String mobile, user_id;


    @Override
    protected void initializeDagger() {
        ((App)getActivity().getApplicationContext()).getNetComponent().verifyDonationInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new VerifyDonationPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify_donation;
    }

    @Override
    protected void initialization() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("انتظر من فضلك");
        sign3bundle = new Bundle();
        signUp3Fragment = new SignUp3Fragment();
        bundle =getArguments();
        if (bundle != null) {
            mobile = bundle.getString("mobile");
            user_id = bundle.getString("user_id");
        }
    }

    @Override
    public void navigateToSignUP(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        sign3bundle.putString("user_id", user_id);
        signUp3Fragment.setArguments(sign3bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, signUp3Fragment).commit();
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


    @OnClick(R.id.search)
    void checkDonation(View view){
        if(donationValue.getText().toString().isEmpty())
            donationValue.setError("من فضلك ادخل قيمة التبرع");
        else {
            DoantionData doantionData = new DoantionData(mobile, donationValue.getText().toString());
            presenter.checkDonation(doantionData);
            showDialog();
        }
    }
}
