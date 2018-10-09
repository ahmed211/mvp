package com.darorman.gm3yaorman.ui.orman_branches;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.orman_branches.GetDepInfoResult;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.usecase.Usecase;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrmanBranchesFragment extends BaseFragment implements BranchesContract.BranchesView{

  //  private View view;
    @Inject
    Usecase usecase;
    BranchesPresenter presenter;
    @BindView(R.id.branch_recycler)
    RecyclerView branchRecycle;
//    @BindView(R.id.branch_name_dialog)
    TextView branchNameDialog;
//    @BindView(R.id.branch_address_dialog)
    TextView branchAddressDialog;
//    @BindView(R.id.branch_phone_dialog)
    TextView branchPhoneDialog;
    ImageView closeDialog;
    RecyclerView.LayoutManager branchManager;
    private OrmanBranchesAdapter branchesAdapter;
    private Dialog dialog;

    @Override
    public void onAttach(Context context) {
        //((App) (context.getApplicationContext()).getNetComponent().inject(this));
        super.onAttach(context);
    }

    public OrmanBranchesFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initializeDagger() {
        ((App) getContext().getApplicationContext()).getNetComponent().branchesInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new BranchesPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
        presenter.getOrmanBranches();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_orman_branches;
    }

    @Override
    protected void initialization() {
        branchManager = new LinearLayoutManager(getActivity());
        branchRecycle.setLayoutManager(branchManager);
        progressDialog = new ProgressDialog(getActivity());
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.orman_branch_dialog);
        branchAddressDialog = dialog.findViewById(R.id.branch_address_dialog);
        branchNameDialog = dialog.findViewById(R.id.branch_name_dialog);
        branchPhoneDialog = dialog.findViewById(R.id.branch_phone_dialog);
        closeDialog = dialog.findViewById(R.id.close_dialog);

    }

    @Override
    public void showBranches(List<GetDepInfoResult> branchesData) {
        branchesAdapter = new OrmanBranchesAdapter(branchesData, presenter.getRecyclerItemLisener());
        branchRecycle.setAdapter(branchesAdapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), "a7aaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
        Log.e("a7a", message);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void showDetailsDialog(GetDepInfoResult branchData) {
        branchAddressDialog.setText(branchData.getOfficeAddrees());
        branchPhoneDialog.setText(branchData.getOfficePhone());
        branchNameDialog.setText(" عنوان و ارقام تليفونات " + branchData.getOfficeName());

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}
