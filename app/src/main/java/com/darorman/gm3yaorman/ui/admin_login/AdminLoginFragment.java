package com.darorman.gm3yaorman.ui.admin_login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.admin_login.AdminData;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.ui.admin_home.AdminHomeFragment;
import com.darorman.gm3yaorman.ui.admin_main.AdminActivity;
import com.darorman.gm3yaorman.ui.orman_activities.ActivitiesFragment;
import com.darorman.gm3yaorman.usecase.Usecase;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminLoginFragment extends BaseFragment implements AdminLoginContract.AdminLoginView {


    @Inject
    Usecase usecase;
    private AdminLoginPresenter presenter;
    private ArrayAdapter<String> officeAdapter ;
    private SharedPrefsHelper prefsHelper;
    private AdminData adminData;
    private List<String> officeID;

    @BindView(R.id.office)
    Spinner officeSpiner;
    @BindView(R.id.username)
    TextView userName;
    @BindView(R.id.password)
    TextView password;
    @Override
    protected void initializeDagger() {
        ((App) getActivity().getApplicationContext()).getNetComponent().adminLoginInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new AdminLoginPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
        presenter.getOffices();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_admin_login;
    }

    @Override
    protected void initialization() {
        prefsHelper = new SharedPrefsHelper(getActivity());
        progressDialog = new ProgressDialog(getActivity());
    }

    @Override
    public void showOffices(List<String> officeName, List<String> officeID) {
        AdminLoginFragment.this.officeID = officeID;
        officeAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, officeName);
        officeSpiner.setAdapter(officeAdapter);

    }

    @Override
    public void navigateToAdminScreens(String id) {
        prefsHelper.putUserID(null);
        prefsHelper.putAdminID(id);
        Toast.makeText(getActivity(), getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), AdminActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.frame, new AdminHomeFragment()).commit();
//        ((AdminActivity)getActivity()).updateTileName("الرئيسية");

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
        if(validate()){
            adminData = new AdminData(userName.getText().toString(),
                    password.getText().toString(),
                    officeID.get((int) officeSpiner.getSelectedItemId()));
            presenter.login(adminData);
            showDialog();
        }
    }

    private boolean validate() {
        if (userName.getText().toString().isEmpty()){
            userName.setError(getResources().getString(R.string.user_name_error));
            return false;
        }
        else if (password.getText().toString().isEmpty()){
            password.setError(getResources().getString(R.string.password_error));
            return false;
        }
        else if (officeSpiner.getSelectedItemId() == 0){
            Toast.makeText(getActivity(), getResources().getString(R.string.office_error), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
