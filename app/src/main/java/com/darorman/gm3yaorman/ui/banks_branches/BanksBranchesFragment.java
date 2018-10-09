package com.darorman.gm3yaorman.ui.banks_branches;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.banks.Bankes;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.di.NetComponent;
import com.darorman.gm3yaorman.usecase.Usecase;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanksBranchesFragment extends BaseFragment implements BanksContract.BanksView {

    @BindView(R.id.banks_recycler)
    RecyclerView banksRecycler;
    @BindView(R.id.details_layout)
    LinearLayout detailsLayout;
    @BindView(R.id.branch_name)
    TextView branchName;
    @BindView(R.id.bank_phone)
    TextView bankPhone;
    @BindView(R.id.soft_code)
    TextView bankSoftCode;
    RecyclerView.LayoutManager banksManager;
    private BanksPresenter presenter;
    @Inject
    Usecase usecase;
    private BanksAdapter banksAdapter;
    @Override
    protected void initializeDagger() {
        ((App) getActivity().getApplicationContext()).getNetComponent().banksInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new BanksPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
        presenter.getBanksAccounts();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_banks_branches;
    }

    @Override
    protected void initialization() {
        banksManager = new LinearLayoutManager(getActivity());
        banksRecycler.setLayoutManager(banksManager);
        progressDialog = new ProgressDialog(getActivity());

    }

    @Override
    public void showBanks(List<Bankes> bankes) {
        banksAdapter = new BanksAdapter(bankes, detailsLayout, this);
        banksRecycler.setAdapter(banksAdapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void showDetailsDialog() {
    }

    @OnClick(R.id.close_details)
    void closeDetails(View view){
        detailsLayout.setVisibility(View.GONE);
    }
}
