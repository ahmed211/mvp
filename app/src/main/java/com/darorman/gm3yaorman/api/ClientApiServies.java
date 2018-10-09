package com.darorman.gm3yaorman.api;

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
import com.darorman.gm3yaorman.utilities.Constants;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientApiServies {

    @GET(Constants.ORMAN_BRANCHES)
    Observable<PojoOrmanBranches> getOrmanBranches();

    @GET(Constants.ORMAN_ACTIVITIES)
    Observable<PojoActivities> getOrmanActivities();

    @GET(Constants.BANKS)
    Observable<PojoBanks> getBanksAccounts();

    @GET(Constants.PROVINES)
    Observable<PojoProvince> getProvince();

    @GET(Constants.ORMAN_OFFICES)
    Observable<PojoOffice> getOffices();

    @POST(Constants.City)
    Observable<PojoCity> getCity(@Body CityData cityData);

    @POST(Constants.SIGNUP1)
    Observable<PojoSignup1> creatAccount(@Body SignUpData1 signUpData1);

    @POST(Constants.SIGNUP2)
    Observable<PojoSignup1> creatAccount2(@Body SignUpData2 signUpData2);

    @POST(Constants.LOGIN)
    Observable<Pojo> login(@Body UserData userData);

    @GET(Constants.CURRENCY)
    Observable<Pojo> currency();

    @GET(Constants.DONATION_TYPE)
    Observable<Pojo> getDonationsType();

    @GET(Constants.DONATION_GOAL)
    Observable<Pojo> getDonationGoal();

    @POST(Constants.MANDOB_ORDER)
    Observable<Pojo> orderMandob(@Body OrderData orderData);

    @POST(Constants.ADMIN_LOGIN)
    Observable<Pojo> adminLogin(@Body AdminData adminData);

    @POST(Constants.GET_MNDOB_ORDERS)
    Observable<Pojo> getMndobOrders(@Body MndobData mndobData);

    @POST(Constants.UPDATE_MNDOB_DATA)
    Observable<Pojo> updateOrderData(@Body UpdateOrderData updateOrderData);

    @POST(Constants.MOBILE_VERIFY)
    Observable<Pojo> verifyMobileUsers(@Body MobileData mobileData);

    @POST(Constants.CHECK_DONATION)
    Observable<Pojo> checkDonation(@Body DoantionData doantionData);

    @POST(Constants.GET_ADMIN_CHAT)
    Observable<Pojo> getAdminChat(@Body AdminChatData chatData);

    @POST(Constants.GET_CHAT_MESSAGES)
    Observable<Pojo> getChatMessages(@Body GetChatModel getChatModel);

    @POST(Constants.SEND_MESSAGES)
    Observable<Pojo> sendChatMessages(@Body MessageData messageData);

}
