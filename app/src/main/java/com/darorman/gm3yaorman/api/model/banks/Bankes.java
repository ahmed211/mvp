package com.darorman.gm3yaorman.api.model.banks;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/5/2018.
 */
public class Bankes {
    private String Bankesid;

    private String BankTitel;

    private String BankeBranch;

    private List<AccountNum> AccountNum;

    private String Softcode;

    public String getBankesid ()
    {
        return Bankesid;
    }

    public void setBankesid (String Bankesid)
    {
        this.Bankesid = Bankesid;
    }

    public String getBankTitel ()
    {
        return BankTitel;
    }

    public void setBankTitel (String BankTitel)
    {
        this.BankTitel = BankTitel;
    }

    public String getBankeBranch ()
    {
        return BankeBranch;
    }

    public void setBankeBranch (String BankeBranch)
    {
        this.BankeBranch = BankeBranch;
    }

    public List<AccountNum> getAccountNum() {
        return AccountNum;
    }

    public String getSoftcode ()
    {
        return Softcode;
    }

    public void setSoftcode (String Softcode)
    {
        this.Softcode = Softcode;
    }

}
