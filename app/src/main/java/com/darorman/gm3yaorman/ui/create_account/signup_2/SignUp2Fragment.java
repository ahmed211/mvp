package com.darorman.gm3yaorman.ui.create_account.signup_2;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.SignUP.SignUpData1;
import com.darorman.gm3yaorman.api.model.spinners.city.CityData;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.ui.create_account.signup_3.SignUp3Fragment;
import com.darorman.gm3yaorman.usecase.Usecase;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp2Fragment extends BaseFragment implements SignUp2Contract.SignUp2View {

    @Inject
    Usecase usecase;
    private SignUp2Presenter presenter;
    @BindView(R.id.province)
    Spinner province_spinner;
    @BindView(R.id.office)
    Spinner office_spinner;
    @BindView(R.id.city)
    Spinner city_spinner;
    @BindView(R.id.address)
    EditText address;
    private ArrayAdapter<String> provinceAdapter, officeAdapter, cityAdapter;
    private Bundle bundle, sign3bundle;
    private SignUpData1 signUpData1, signUpData2;
    private String cityID, provinceID, officeID;
    private SignUp3Fragment signUp3Fragment;
    @Override
    protected void initializeDagger() {
        ((App) getContext().getApplicationContext()).getNetComponent().signUpInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new SignUp2Presenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
        presenter.getProvince();
        presenter.getOffices();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign_up2;
    }

    @Override
    protected void initialization() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("انتظر من فضلك");
        sign3bundle = new Bundle();
        signUp3Fragment = new SignUp3Fragment();
        bundle =getArguments();
        if (bundle != null)
            signUpData1 = bundle.getParcelable("signup_data");

        if(getActivity().getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }


    @Override
    public void showProvince(List<String> provinceName, List<String> provinceID) {
        provinceAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, provinceName);
        province_spinner.setAdapter(provinceAdapter);

        province_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0) {
                    Log.v("a7a", provinceID.get(i));
                    CityData cityData = new CityData(Integer.valueOf(provinceID.get(i)));
                    presenter.getCities(cityData);
                    showDialog();
                    SignUp2Fragment.this.provinceID = provinceID.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showOffice(List<String> officeName, List<String> officeID) {
        officeAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, officeName);
        office_spinner.setAdapter(officeAdapter);

        office_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SignUp2Fragment.this.officeID = officeID.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showCity(List<String> cityName, List<String> cityID) {
        cityAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, cityName);
        city_spinner.setAdapter(cityAdapter);
        dismissDialog();

        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SignUp2Fragment.this.cityID = cityID.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), "nooooooooooooo", Toast.LENGTH_SHORT).show();
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
    public void navigateToSignUp3(String id) {
        sign3bundle.putString("user_id", id);
        signUp3Fragment.setArguments(sign3bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, signUp3Fragment).commit();
        dismissDialog();
    }

    @OnClick(R.id.register)
    void creatAccount(View view){
        if (validate()){
            signUpData2 = new SignUpData1(signUpData1.getPerson_name(), provinceID, cityID,
                    address.getText().toString(),signUpData1.getMobile(),signUpData1.getPhoneHome(),officeID);
            presenter.creatAccount(signUpData2);
            showDialog();
        }
    }

    private boolean validate() {
        if (address.getText().toString().isEmpty()){
            address.setError("من فضلك ادخل العنوان");
            return false;
        }
        else if (province_spinner.getSelectedItemId() == 0){
            Toast.makeText(getActivity(), "من فضلك اختار المحافظة", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (city_spinner.getSelectedItemId() == 0){
            Toast.makeText(getActivity(), "من فضلك اختار المدينة", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (office_spinner.getSelectedItemId() == 0){
            Toast.makeText(getActivity(), "من فضلك اختار المكتب", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
