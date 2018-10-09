package com.darorman.gm3yaorman.api.model.verify_donation;

/**
 * Created by Ahmed Mostafa on 9/24/2018.
 */
public class CheckResult {
    private String trans_date;

    private String Message;

    private String donation_money;

    public String getTrans_date ()
    {
        return trans_date;
    }

    public void setTrans_date (String trans_date)
    {
        this.trans_date = trans_date;
    }

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    public String getDonation_money ()
    {
        return donation_money;
    }

    public void setDonation_money (String donation_money)
    {
        this.donation_money = donation_money;
    }
}
