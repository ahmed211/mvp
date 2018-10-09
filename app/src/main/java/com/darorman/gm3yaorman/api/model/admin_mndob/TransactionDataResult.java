package com.darorman.gm3yaorman.api.model.admin_mndob;

/**
 * Created by Ahmed Mostafa on 9/19/2018.
 */
public class TransactionDataResult {
    private String Don_Name;

    private String Donationtypename;

    private String Person_Name;

    private String Trans_Head_ID;

    private String Don_Amount;

    private String Cur_Name;

    public String getDon_Name ()
    {
        return Don_Name;
    }

    public void setDon_Name (String Don_Name)
    {
        this.Don_Name = Don_Name;
    }

    public String getDonationtypename ()
    {
        return Donationtypename;
    }

    public void setDonationtypename (String Donationtypename)
    {
        this.Donationtypename = Donationtypename;
    }

    public String getPerson_Name ()
    {
        return Person_Name;
    }

    public void setPerson_Name (String Person_Name)
    {
        this.Person_Name = Person_Name;
    }

    public String getTrans_Head_ID ()
    {
        return Trans_Head_ID;
    }

    public void setTrans_Head_ID (String Trans_Head_ID)
    {
        this.Trans_Head_ID = Trans_Head_ID;
    }

    public String getDon_Amount ()
    {
        return Don_Amount;
    }

    public void setDon_Amount (String Don_Amount)
    {
        this.Don_Amount = Don_Amount;
    }

    public String getCur_Name ()
    {
        return Cur_Name;
    }

    public void setCur_Name (String Cur_Name)
    {
        this.Cur_Name = Cur_Name;
    }

}
