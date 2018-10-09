package com.darorman.gm3yaorman.api.model.orman_branches;

/**
 * Created by Ahmed Mostafa on 9/2/2018.
 */
public class GetDepInfoResult {
    private String Officeid;

    private String OfficePhone;

    private String OfficeName;

    private String OfficeAddrees;

    public String getOfficeid ()
    {
        return Officeid;
    }

    public void setOfficeid (String Officeid)
    {
        this.Officeid = Officeid;
    }

    public String getOfficePhone ()
    {
        return OfficePhone;
    }

    public void setOfficePhone (String OfficePhone)
    {
        this.OfficePhone = OfficePhone;
    }

    public String getOfficeName ()
    {
        return OfficeName;
    }

    public void setOfficeName (String OfficeName)
    {
        this.OfficeName = OfficeName;
    }

    public String getOfficeAddrees ()
    {
        return OfficeAddrees;
    }

    public void setOfficeAddrees (String OfficeAddrees)
    {
        this.OfficeAddrees = OfficeAddrees;
    }
}
