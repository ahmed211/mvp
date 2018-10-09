package com.darorman.gm3yaorman.ui.create_account.signup_2;

import com.darorman.gm3yaorman.api.model.SignUP.SignUpData1;
import com.darorman.gm3yaorman.api.model.SignUP.signup_result.PojoSignup1;
import com.darorman.gm3yaorman.api.model.spinners.city.CityData;
import com.darorman.gm3yaorman.api.model.spinners.city.CityResult;
import com.darorman.gm3yaorman.api.model.spinners.city.PojoCity;
import com.darorman.gm3yaorman.api.model.spinners.orman_officses.DepartmentResult;
import com.darorman.gm3yaorman.api.model.spinners.orman_officses.PojoOffice;
import com.darorman.gm3yaorman.api.model.spinners.province.PojoProvince;
import com.darorman.gm3yaorman.api.model.spinners.province.ProvinceResult;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/12/2018.
 */
public class SignUp2Presenter extends BasePresenter<SignUp2Contract.SignUp2View> implements SignUp2Contract.SignUp2Presenter {

    private Scheduler mainScheduler, ioScheduler;
    private Usecase usecase;
    private PojoProvince province;
    private List<String> provinceName, provinceID, officeName, officeID, cityName, cityID;

    public SignUp2Presenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
    }

    @Override
    public void getProvince() {
        checkViewAttached();
        addDisposal(usecase.getProvince()
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<PojoProvince>() {
            @Override
            public void onNext(PojoProvince pojoProvince) {
                getProvinceLists(pojoProvince.getProvinceResult());
                getView().showProvince(provinceName, provinceID);
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        }));
    }

    @Override
    public void getOffices() {
        checkViewAttached();
        addDisposal(usecase.getOffices()
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<PojoOffice>() {
            @Override
            public void onNext(PojoOffice pojoOffice) {
                getOfficesLists(pojoOffice.getDepartmentResult());
                getView().showOffice(officeName, officeID);
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        }));
    }

    @Override
    public void getCities(CityData cityData) {
        checkViewAttached();
        addDisposal(usecase.getCity(cityData)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeWith(new DisposableObserver<PojoCity>() {

                    @Override
                    public void onNext(PojoCity pojoCity) {
                        getCitiesLists(pojoCity.getCityResult());
                        getView().showCity(cityName, cityID);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    @Override
    public void creatAccount(SignUpData1 signUpData1) {
        checkViewAttached();
        addDisposal(usecase.createAccount(signUpData1)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeWith(new DisposableObserver<PojoSignup1>() {
                    @Override
                    public void onNext(PojoSignup1 pojoSignup1) {
                        if (pojoSignup1.getDonatorResult().getId().equals("0"))
                            getView().showError(pojoSignup1.getDonatorResult().getMessage());
                        else
                            getView().navigateToSignUp3(pojoSignup1.getDonatorResult().getId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void getCitiesLists(List<CityResult> cityList) {
        cityName = new ArrayList<>();
        cityID = new ArrayList<>();
        cityName.add("اختر المدينة");
        cityID.add("0");
        for(int i=0; i<cityList.size(); i++){
            cityName.add(cityList.get(i).getPlace_Name());
            cityID.add(cityList.get(i).getPlace_Id());
        }

    }

    private void getOfficesLists(List<DepartmentResult> officesList) {
        officeName = new ArrayList<>();
        officeID = new ArrayList<>();
        officeName.add("اختر المكتب");
        officeID.add("0");
        for(int i=0; i<officesList.size(); i++){
            officeName.add(officesList.get(i).getDep_Name());
            officeID.add(officesList.get(i).getDep_ID());
        }
    }

    private void getProvinceLists(List<ProvinceResult> provinceList) {
        provinceName = new ArrayList<>();
        provinceID = new ArrayList<>();
        provinceName.add("اختر المحافظة");
        provinceID.add("0");
        for(int i=0; i<provinceList.size(); i++){
            provinceName.add(provinceList.get(i).getPlace_Name());
            provinceID.add(provinceList.get(i).getPlace_Id());
        }
    }
}
