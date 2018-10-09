package com.darorman.gm3yaorman.ui.admin_login;

import com.darorman.gm3yaorman.api.model.Pojo;
import com.darorman.gm3yaorman.api.model.admin_login.AdminData;
import com.darorman.gm3yaorman.api.model.login.user_login.UserData;
import com.darorman.gm3yaorman.api.model.spinners.orman_officses.DepartmentResult;
import com.darorman.gm3yaorman.api.model.spinners.orman_officses.PojoOffice;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.ui.login.LoginContract;
import com.darorman.gm3yaorman.usecase.Usecase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/13/2018.
 */
public class AdminLoginPresenter extends BasePresenter<AdminLoginContract.AdminLoginView> implements AdminLoginContract.AdminLoginPresenter{

    Scheduler mainScheduler, ioScheduler;
    Usecase usecase;
    private List<String>  officeName, officeID;

    public AdminLoginPresenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.usecase = usecase;
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
                        getView().showOffices(officeName, officeID);
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
    public void login(AdminData adminData) {
        checkViewAttached();
        addDisposal(usecase.adminLogin(adminData)
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<Pojo>() {
            @Override
            public void onNext(Pojo pojo) {
                if (pojo.getAdminLoginResult().getId().equals("0"))
                    getView().showError(pojo.getAdminLoginResult().getMessage());
                else
                    getView().navigateToAdminScreens(pojo.getAdminLoginResult().getId());
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

    private void getOfficesLists(List<DepartmentResult> officesList) {
        officeName = new ArrayList<>();
        officeID = new ArrayList<>();
        officeName.add("اختر الفرع");
        officeID.add("0");
        for(int i=0; i<officesList.size(); i++){
            officeName.add(officesList.get(i).getDep_Name());
            officeID.add(officesList.get(i).getDep_ID());
        }
    }
}
