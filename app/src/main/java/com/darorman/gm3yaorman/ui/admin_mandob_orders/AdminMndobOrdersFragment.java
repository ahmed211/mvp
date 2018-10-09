package com.darorman.gm3yaorman.ui.admin_mandob_orders;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.admin_mndob.MndobData;
import com.darorman.gm3yaorman.api.model.admin_mndob.TransactionDataResult;
import com.darorman.gm3yaorman.api.model.representative.OrderData;
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
public class AdminMndobOrdersFragment extends BaseFragment implements AdminMndobContract.AdminMndobView {

    @Inject
    Usecase usecase;
    @BindView(R.id.mndob_recycler)
    RecyclerView mndobRecycler;
    private RecyclerView.LayoutManager mndobManager;
    private MndobOrdersAdapter ordersAdapter;
    private AdminMndobOrdersPresenter presenter;
    private SharedPrefsHelper prefsHelper;
    private MndobData mndobData;
    List<TransactionDataResult> data;

    @Override
    protected void initializeDagger() {
        ((App) getActivity().getApplicationContext()).getNetComponent().adminMndobOrdersInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new AdminMndobOrdersPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
        mndobData = new MndobData(prefsHelper.getAdminID()); ///46
        presenter.getMndobOrders(mndobData);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_admin_mndob_orders;
    }

    @Override
    protected void initialization() {
        mndobManager = new LinearLayoutManager(getActivity());
        mndobRecycler.setLayoutManager(mndobManager);
        prefsHelper = new SharedPrefsHelper(getActivity());
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
    public void showMndobData(List<TransactionDataResult> mndobData) {
        ordersAdapter = new MndobOrdersAdapter(mndobData, presenter);
        mndobRecycler.setAdapter(ordersAdapter);
        data = mndobData;
    }

    @Override
    public void updateList(int position) {
        data.remove(position);
        ordersAdapter.notifyItemRemoved(position);
        ordersAdapter.notifyItemRangeChanged(position, data.size());
        Toast.makeText(getActivity(), "تم حذف الطلب", Toast.LENGTH_SHORT).show();
    }
}
