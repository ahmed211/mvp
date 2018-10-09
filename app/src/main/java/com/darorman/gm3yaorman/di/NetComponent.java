package com.darorman.gm3yaorman.di;

import com.darorman.gm3yaorman.di.module.AppModule;
import com.darorman.gm3yaorman.di.module.NetModule;
import com.darorman.gm3yaorman.ui.admin_chat_list.AdminChatFragment;
import com.darorman.gm3yaorman.ui.admin_main.AdminActivity;
import com.darorman.gm3yaorman.ui.admin_login.AdminLoginFragment;
import com.darorman.gm3yaorman.ui.admin_mandob_orders.AdminMndobOrdersFragment;
import com.darorman.gm3yaorman.ui.banks_branches.BanksBranchesFragment;
import com.darorman.gm3yaorman.ui.chat.ChatFragment;
import com.darorman.gm3yaorman.ui.create_account.signup_2.SignUp2Fragment;
import com.darorman.gm3yaorman.ui.create_account.signup_3.SignUp3Fragment;
import com.darorman.gm3yaorman.ui.create_account.verify_donation.VerifyDonationFragment;
import com.darorman.gm3yaorman.ui.create_account.verify_mobile.VerifyMobileFragment;
import com.darorman.gm3yaorman.ui.home.HomeActivity;
import com.darorman.gm3yaorman.ui.login.LoginFragment;
import com.darorman.gm3yaorman.ui.orman_acitivities_details.OrmanActivitiesDetailsFragment;
import com.darorman.gm3yaorman.ui.orman_activities.ActivitiesFragment;
import com.darorman.gm3yaorman.ui.orman_branches.OrmanBranchesFragment;
import com.darorman.gm3yaorman.ui.representative.RepresentativeFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {AppModule.class , NetModule.class})
public interface NetComponent {

    void inject(HomeActivity homeActivity);
    void branchesInject(OrmanBranchesFragment ormanBranchesFragment);
    void activitiesInject(ActivitiesFragment activitiesFragment);
    void activitiesDetailsInject(OrmanActivitiesDetailsFragment ormanActivitiesDetailsFragment);
    void banksInject(BanksBranchesFragment banksBranchesFragment);
    void signUpInject(SignUp2Fragment signUp2Fragment);
    void signUpInject(SignUp3Fragment signUp3Fragment);
    void loginInject(LoginFragment loginFragment);
    void representativeInject(RepresentativeFragment representativeFragment);
    void adminLoginInject(AdminLoginFragment adminLoginFragment);
    void adminHomeInject(AdminActivity adminHomeActivity);
    void adminMndobOrdersInject(AdminMndobOrdersFragment adminMndobOrdersFragment);
    void verifyMobileInject(VerifyMobileFragment verifyMobileFragment);
    void verifyDonationInject(VerifyDonationFragment verifyDonationFragment);
    void adminChatListInject(AdminChatFragment adminChatFragment);
    void chatInject(ChatFragment chatFragment);
}
