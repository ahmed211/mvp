package com.darorman.gm3yaorman.usecase;

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

import io.reactivex.Observable;
import retrofit2.http.Body;

public interface Usecase{
    Observable<PojoOrmanBranches> getOrmanBranches();
    Observable<PojoActivities> getOrmanActivities();
    Observable<PojoBanks> getBanksAccounts();
    Observable<PojoProvince> getProvince();
    Observable<PojoOffice> getOffices();
    Observable<PojoCity> getCity(CityData cityData);
    Observable<PojoSignup1> createAccount(SignUpData1 signUpData1);
    Observable<PojoSignup1> createAccount2(SignUpData2 signUpData2);
    Observable<Pojo> login(UserData userData);
    Observable<Pojo> currency();
    Observable<Pojo> getDonationsType();
    Observable<Pojo> getDonationGoal();
    Observable<Pojo> orderMandob(OrderData orderData);
    Observable<Pojo> adminLogin(AdminData adminData);
    Observable<Pojo> getMndobOrders(MndobData mndobData);
    Observable<Pojo> updateOrderData(UpdateOrderData updateOrderData);
    Observable<Pojo> verifyMobileUsers(MobileData mobileData);
    Observable<Pojo> checkDonation(DoantionData doantionData);
    Observable<Pojo> getAdminChat(AdminChatData chatData);
    Observable<Pojo> getChatMessages(GetChatModel getChatModel);
    Observable<Pojo> sendChatMessages(MessageData messageData);



}
