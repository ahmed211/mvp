package com.darorman.gm3yaorman.ui.orman_branches;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.orman_branches.GetDepInfoResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ahmed Mostafa on 9/3/2018.
 */
public class DialogViews extends Dialog{

    private Unbinder unbinder;
    private Activity activity;
    private GetDepInfoResult branchData;
    private View view;
    @BindView(R.id.branch_name_dialog)
    TextView branchNameDialog;
    @BindView(R.id.branch_address_dialog)
    TextView branchAddressDialog;
    @BindView(R.id.branch_phone_dialog)
    TextView branchPhoneDialog;
    public DialogViews(@NonNull Activity activity, GetDepInfoResult branchData) {
        super(activity);
        this.activity = activity;
        this.branchData = branchData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orman_branch_dialog);

        LayoutInflater inflater = LayoutInflater.from(activity);
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        View viewDialog = inflater.inflate(R.layout.orman_branch_dialog, viewGroup, false);
        ButterKnife.bind(this, viewDialog);
       // view = View.inflate(getContext(), R.layout.orman_branch_dialog, null);
       // unbinder = ButterKnife.bind(activity, view);

        setData();
    }

    private void setData() {
        branchAddressDialog.setText(branchData.getOfficeAddrees());
        branchPhoneDialog.setText(branchData.getOfficePhone());
        branchNameDialog.setText(branchData.getOfficeName() + " عنوان و ارقام تليفونات ");
    }
}
