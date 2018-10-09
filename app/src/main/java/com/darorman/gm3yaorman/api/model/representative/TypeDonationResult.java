package com.darorman.gm3yaorman.api.model.representative;

/**
 * Created by Ahmed Mostafa on 9/17/2018.
 */
public class TypeDonationResult {
    private String donationtypename;

    private String donationtypeid;

    public String getDonationtypename ()
    {
        return donationtypename;
    }

    public void setDonationtypename (String donationtypename)
    {
        this.donationtypename = donationtypename;
    }

    public String getDonationtypeid ()
    {
        return donationtypeid;
    }

    public void setDonationtypeid (String donationtypeid)
    {
        this.donationtypeid = donationtypeid;
    }
}
