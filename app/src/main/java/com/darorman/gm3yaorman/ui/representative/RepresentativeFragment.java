package com.darorman.gm3yaorman.ui.representative;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.representative.OrderData;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.ui.orman_activities.ActivitiesFragment;
import com.darorman.gm3yaorman.usecase.Usecase;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepresentativeFragment extends BaseFragment implements RepresentativeContract.RepresentativeView{

    @Inject
    Usecase usecase;
    private RepresentativePresenter presenter;
    @BindView(R.id.currency)
    Spinner currencySpinner;
    @BindView(R.id.donnation_goal)
    Spinner goalSpinner;
    @BindView(R.id.donnation_type)
    Spinner typeSpinner;
    @BindView(R.id.donation_value)
    TextView donationValue;
    @BindView(R.id.notes)
    TextView notes;
    private ArrayAdapter<String> currencyAdapter, goalAdapter, typeAdapter;
    private List<String> currencyName, currencyID, goalName, goalID, typeName, typeID;
    private SharedPrefsHelper prefsHelper;
    private String userID;

    @Override
    protected void initializeDagger() {
        ((App) getActivity().getApplicationContext()).getNetComponent().representativeInject(this);
    }

    @Override
    protected void intializePresenter() {
        presenter = new RepresentativePresenter(AndroidSchedulers.mainThread(), Schedulers.io(), usecase);
        presenter.attachView(this);
        presenter.getCurrency();
        presenter.getDonationType();
        presenter.getDonationGoal();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_representative;
    }

    @Override
    protected void initialization() {
        prefsHelper = new SharedPrefsHelper(getActivity());
        userID = prefsHelper.getUserID();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void showCurrency(List<String> currencyName, List<String> currencyID) {
        this.currencyID = currencyID;
        currencyAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, currencyName);
        currencySpinner.setAdapter(currencyAdapter);
    }

    @Override
    public void showDonationTypes(List<String> typeName, List<String> typeID) {
        this.typeID = typeID;
        typeAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, typeName);
        typeSpinner.setAdapter(typeAdapter);
    }

    @Override
    public void showDonationGoal(List<String> goalName, List<String> goalID) {
        this.goalID=goalID;
        goalAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, goalName);
        goalSpinner.setAdapter(goalAdapter);
    }

    @Override
    public void showOrderMandobResult(String message) {
        Toast.makeText(getActivity(), "تم طلب المندوب بنجاح", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new ActivitiesFragment()).commit();
    }

    @OnClick(R.id.order)
    void getMndob(View view){

        if (validate()){
            OrderData orderData = new OrderData(goalID.get((int) goalSpinner.getSelectedItemId()),
                    typeID.get((int) typeSpinner.getSelectedItemId()),
                    currencyID.get((int) currencySpinner.getSelectedItemId()),
                    notes.getText().toString(), donationValue.getText().toString()
                    , userID);

            presenter.makeOrder(orderData);
        }
    }

    private boolean validate() {
        if (donationValue.getText().toString().isEmpty())
        {
            donationValue.setError("من فضلك ادخل قيمة التبرع");
            return false;
        }
        else if (currencySpinner.getSelectedItemId() == 0){
            Toast.makeText(getActivity(), "من فضلك اختر العملة", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (typeSpinner.getSelectedItemId() == 0){
            Toast.makeText(getActivity(), "من فضلك اختر نوع التبرع", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (goalSpinner.getSelectedItemId() == 0){
            Toast.makeText(getActivity(), "من فضلك اختر هدف التبرع", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
