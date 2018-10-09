package com.darorman.gm3yaorman.ui.create_account.verify_mobile;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.verify_mobile.GetDATAResult;
import com.darorman.gm3yaorman.api.model.verify_mobile.MobileData;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.ui.create_account.SignUpFragment;
import com.darorman.gm3yaorman.ui.create_account.verify_donation.VerifyDonationFragment;
import com.darorman.gm3yaorman.ui.home.HomeActivity;
import com.darorman.gm3yaorman.usecase.Usecase;
import com.darorman.gm3yaorman.utilities.AppUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyMobileFragment extends BaseFragment implements VerifyMobileContract.VerifyMobileView {

    @Inject
    Usecase usecase;
    @BindView(R.id.user_recycler)
    RecyclerView userRecycler;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.mobile_search)
    Button search;
    @BindView(R.id.create_account)
    Button createAccount;
    private RecyclerView.LayoutManager userManager;
    private VerifyMobilePresenter presenter;
    private VerifyUserAdapter userAdapter;
    private Bundle bundle;
    private VerifyDonationFragment donationFragment;
    @Override
    protected void initializeDagger() {
        ((App) getActivity().getApplicationContext()).getNetComponent().verifyMobileInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new VerifyMobilePresenter(AndroidSchedulers.mainThread(),
                Schedulers.io(), usecase);
        presenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify_mobile;
    }

    @Override
    protected void initialization() {
        userManager = new LinearLayoutManager(getActivity());
        userRecycler.setLayoutManager(userManager);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("انتظر من فضلك");
        bundle = new Bundle();
        donationFragment = new VerifyDonationFragment();
    }

    @Override
    public void showError(String message) {
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

    @Override
    public void showUsers(List<GetDATAResult> usersData) {
        userAdapter = new VerifyUserAdapter(usersData, mobile.getText().toString());
        userRecycler.setAdapter(userAdapter);
        search.setVisibility(View.GONE);
        mobile.setEnabled(false);
        AppUtils.hideKeyboard(getActivity());
        dismissDialog();

    }



    @Override
    public void navigateToVerifyDonation() {
        bundle.putString("mobile", mobile.getText().toString());
        donationFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, donationFragment);
    }

    @OnClick(R.id.mobile_search)
    void getUser(View view){
        if (mobile.getText().toString().isEmpty())
            mobile.setError("تاكد من ادخال رقم الموبايل");
        else if (mobile.getText().toString().length() != 11)
            mobile.setError("تاكد من ان الموبايل 11 رقم");
        else {
            presenter.getUsers(new MobileData(mobile.getText().toString()));
            showDialog();
        }
    }

    @OnClick(R.id.create_account)
    void navigateToSignUP(View view){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new SignUpFragment()).commit();
        ((HomeActivity)this.getActivity()).updateTileName("انشاء حساب");
    }
    @Override
    public void navigateSignUP() {
        search.setVisibility(View.GONE);
        userRecycler.setVisibility(View.GONE);
        createAccount.setVisibility(View.VISIBLE);
        AppUtils.hideKeyboard(getActivity());
        mobile.setEnabled(false);
        dismissDialog();

    }

}
