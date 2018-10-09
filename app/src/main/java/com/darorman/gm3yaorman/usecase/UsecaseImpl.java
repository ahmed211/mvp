package com.darorman.gm3yaorman.usecase;

import com.darorman.gm3yaorman.api.ClientApiServies;
import com.darorman.gm3yaorman.api.model.Pojo;
import com.darorman.gm3yaorman.api.model.SignUP.SignUpData1;
import com.darorman.gm3yaorman.api.model.SignUP.SignUpData2;
import com.darorman.gm3yaorman.api.model.SignUP.signup_result.PojoSignup1;
import com.darorman.gm3yaorman.api.model.admin_chat.AdminChatData;
import com.darorman.gm3yaorman.api.model.admin_login.AdminData;
import com.darorman.gm3yaorman.api.model.admin_mndob.MndobData;
import com.darorman.gm3yaorman.api.model.admin_mndob.UpdateOrderData;
import com.darorman.gm3yaorman.api.model.banks.PojoBanks;
import com.darorman.gm3yaorman.api.model.chat.GetChatModel;
import com.darorman.gm3yaorman.api.model.chat.MessageData;
import com.darorman.gm3yaorman.api.model.login.user_login.UserData;
import com.darorman.gm3yaorman.api.model.orman_activities.PojoActivities;
import com.darorman.gm3yaorman.api.model.orman_branches.PojoOrmanBranches;
import com.darorman.gm3yaorman.api.model.representative.OrderData;
import com.darorman.gm3yaorman.api.model.spinners.city.CityData;
import com.darorman.gm3yaorman.api.model.spinners.city.PojoCity;
import com.darorman.gm3yaorman.api.model.spinners.orman_officses.PojoOffice;
import com.darorman.gm3yaorman.api.model.spinners.province.PojoProvince;
import com.darorman.gm3yaorman.api.model.verify_donation.DoantionData;
import com.darorman.gm3yaorman.api.model.verify_mobile.MobileData;
import com.darorman.gm3yaorman.application.App;
import com.darorman.gm3yaorman.utilities.Constants;
import com.darorman.gm3yaorman.utilities.NetworkUtils;

import java.io.IOException;

import io.reactivex.Observable;

public class UsecaseImpl implements Usecase{

    private ClientApiServies apiServies;

    public UsecaseImpl(ClientApiServies apiServies) {
        this.apiServies = apiServies;
    }

    @Override
    public Observable<PojoOrmanBranches> getOrmanBranches() {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.getOrmanBranches()).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        else {
            return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
        }
    }

    @Override
    public Observable<PojoActivities> getOrmanActivities() {
        if(NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.getOrmanActivities()).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        else{
            return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
        }
    }

    @Override
    public Observable<PojoBanks> getBanksAccounts() {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.getBanksAccounts()).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        else {
            return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
        }
    }

    @Override
    public Observable<PojoProvince> getProvince() {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.getProvince()).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<PojoOffice> getOffices() {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.getOffices()).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<PojoCity> getCity(CityData cityData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.getCity(cityData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<PojoSignup1> createAccount(SignUpData1 signUpData1) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.creatAccount(signUpData1)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<PojoSignup1> createAccount2(SignUpData2 signUpData2) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.creatAccount2(signUpData2)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> login(UserData userData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.login(userData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> currency() {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.currency()).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> getDonationsType() {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.getDonationsType()).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> getDonationGoal() {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.getDonationGoal()).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> orderMandob(OrderData orderData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.orderMandob(orderData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> adminLogin(AdminData adminData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.adminLogin(adminData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> getMndobOrders(MndobData mndobData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.getMndobOrders(mndobData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> updateOrderData(UpdateOrderData updateOrderData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.updateOrderData(updateOrderData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> verifyMobileUsers(MobileData mobileData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.verifyMobileUsers(mobileData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> checkDonation(DoantionData doantionData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(() -> apiServies.checkDonation(doantionData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> getAdminChat(AdminChatData chatData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(()->apiServies.getAdminChat(chatData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> getChatMessages(GetChatModel getChatModel) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(()->apiServies.getChatMessages(getChatModel)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }

    @Override
    public Observable<Pojo> sendChatMessages(MessageData messageData) {
        if (NetworkUtils.isConnected(App.getContext())){
            return Observable.defer(()->apiServies.sendChatMessages(messageData)).retryWhen(
                    throwableObservable -> throwableObservable.flatMap(throwable -> {
                        if (throwable instanceof IOException){
                            Observable.error(new Throwable(Constants.SERVER_ERROR));
                        }
                        return Observable.error(throwable);
                    })
            );
        }
        return Observable.error(new Throwable(Constants.INTERNET_CONNECTION_ERROR));
    }
}
