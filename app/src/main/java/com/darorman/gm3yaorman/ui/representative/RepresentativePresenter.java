package com.darorman.gm3yaorman.ui.representative;

import com.darorman.gm3yaorman.api.model.Pojo;
import com.darorman.gm3yaorman.api.model.representative.CurrencyResult;
import com.darorman.gm3yaorman.api.model.representative.DonationResult;
import com.darorman.gm3yaorman.api.model.representative.OrderData;
import com.darorman.gm3yaorman.api.model.representative.TypeDonationResult;
import com.darorman.gm3yaorman.base.BasePresenter;
import com.darorman.gm3yaorman.usecase.Usecase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Mostafa on 9/17/2018.
 */
public class RepresentativePresenter extends BasePresenter<RepresentativeContract.RepresentativeView>
        implements RepresentativeContract.RepresentativePresenter {

    private Usecase usecase;
    private Scheduler mainScheduler, ioScheduler;
    private List<String> currencyName, currencyID, goalName, goalID, typeName, typeID;

    public RepresentativePresenter(Scheduler mainScheduler, Scheduler ioScheduler, Usecase usecase) {
        this.usecase = usecase;
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
    }


    @Override
    public void getCurrency() {
        checkViewAttached();
        addDisposal(usecase.currency().subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<Pojo>() {
            @Override
            public void onNext(Pojo pojo) {
                getCurrencyLists(pojo.getCurrencyResult());
                getView().showCurrency(currencyName, currencyID);
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
    public void getDonationType() {
        checkViewAttached();
        addDisposal(usecase.getDonationsType()
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<Pojo>() {
            @Override
            public void onNext(Pojo pojo) {
                getTypeLists(pojo.getTypeDonationResult());
                getView().showDonationTypes(typeName, typeID);
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
    public void getDonationGoal() {
        checkViewAttached();
        addDisposal(usecase.getDonationGoal()
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeWith(new DisposableObserver<Pojo>() {
                    @Override
                    public void onNext(Pojo pojo) {
                        getGoalLists(pojo.getDonationResult());
                        getView().showDonationGoal(goalName, goalID);
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
    public void makeOrder(OrderData orderData) {
        checkViewAttached();
        addDisposal(usecase.orderMandob(orderData)
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribeWith(new DisposableObserver<Pojo>() {
            @Override
            public void onNext(Pojo pojo) {
                getView().showOrderMandobResult(pojo.getTrans_head_insertResult().getMessage());
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

    private void getGoalLists(List<DonationResult> donationResult) {
        goalName = new ArrayList<>();
        goalID = new ArrayList<>();
        goalID.add("0");
        goalName.add("هدف التبرع");

        for (int i=0; i<donationResult.size(); i++){
            goalName.add(donationResult.get(i).getDon_Name());
            goalID.add(donationResult.get(i).getDon_ID());
        }
    }

    private void getTypeLists(List<TypeDonationResult> typeDonationResult) {
        typeName = new ArrayList<>();
        typeID = new ArrayList<>();
        typeID.add("0");
        typeName.add("نوع التبرع");

        for(int i=0; i<typeDonationResult.size(); i++){
            typeName.add(typeDonationResult.get(i).getDonationtypename());
            typeID.add(typeDonationResult.get(i).getDonationtypeid());
        }

    }

    private void getCurrencyLists(List<CurrencyResult> currencyResult) {
        currencyName = new ArrayList<>();
        currencyID = new ArrayList<>();
        currencyID.add("0");
        currencyName.add("العملات");

        for(int i=0; i<currencyResult.size(); i++){
            currencyName.add(currencyResult.get(i).getCur_Name());
            currencyID.add(currencyResult.get(i).getCur_ID());
        }
    }

}
