package com.darorman.gm3yaorman.api.model;

import com.darorman.gm3yaorman.api.model.admin_chat.GetListChatResult;
import com.darorman.gm3yaorman.api.model.admin_login.AdminLoginResult;
import com.darorman.gm3yaorman.api.model.admin_mndob.TransactionDataResult;
import com.darorman.gm3yaorman.api.model.admin_mndob.UpdateTransactionResult;
import com.darorman.gm3yaorman.api.model.chat.ChatMessageResult;
import com.darorman.gm3yaorman.api.model.chat.GetChatResult;
import com.darorman.gm3yaorman.api.model.login.login_result.LoginResult;
import com.darorman.gm3yaorman.api.model.representative.CurrencyResult;
import com.darorman.gm3yaorman.api.model.representative.DonationResult;
import com.darorman.gm3yaorman.api.model.representative.Trans_head_insertResult;
import com.darorman.gm3yaorman.api.model.representative.TypeDonationResult;
import com.darorman.gm3yaorman.api.model.verify_donation.CheckResult;
import com.darorman.gm3yaorman.api.model.verify_mobile.GetDATAResult;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/13/2018.
 */
public class Pojo {
    private LoginResult LoginResult;
    private List<CurrencyResult> CurrencyResult;
    private List<TypeDonationResult> TypeDonationResult;
    private List<DonationResult> DonationResult;
    private Trans_head_insertResult Trans_head_insertResult;
    private AdminLoginResult AdminLoginResult;
    private List<TransactionDataResult> TransactionDataResult;
    private UpdateTransactionResult UpdateTransactionResult;
    private List<GetDATAResult> GetDATAResult;
    private CheckResult CheckResult;
    private List<GetListChatResult> GetListChatResult;
    private List<GetChatResult> GetChatResult;
    private ChatMessageResult ChatMessageResult;

    public ChatMessageResult getChatMessageResult ()
    {
        return ChatMessageResult;
    }
    public List<GetChatResult> getGetChatResult() {
        return GetChatResult;
    }

    public List<GetListChatResult> getGetListChatResult() {
        return GetListChatResult;
    }

    public CheckResult getCheckResult ()
    {
        return CheckResult;
    }

    public List<GetDATAResult> getGetDATAResult() {
        return GetDATAResult;
    }

    public UpdateTransactionResult getUpdateTransactionResult ()
    {
        return UpdateTransactionResult;
    }

    public LoginResult getLoginResult() {
        return LoginResult;
    }

    public List<CurrencyResult> getCurrencyResult() {
        return CurrencyResult;
    }

    public List<TypeDonationResult> getTypeDonationResult() {
        return TypeDonationResult;
    }

    public List<DonationResult> getDonationResult() {
        return DonationResult;
    }

    public Trans_head_insertResult getTrans_head_insertResult() {
        return Trans_head_insertResult;
    }

    public AdminLoginResult getAdminLoginResult() {
        return AdminLoginResult;
    }

    public List<TransactionDataResult> getTransactionDataResult() {
        return TransactionDataResult;
    }
}
