package com.darorman.gm3yaorman.api.model.verify_mobile;

/**
 * Created by Ahmed Mostafa on 9/23/2018.
 */
public class GetDATAResult {
    private String place_name;

    private String Person_Name;

    private String CityName;

    private String StreetTital;

    private String Person_Id;

    public String getPlace_name ()
    {
        return place_name;
    }

    public void setPlace_name (String place_name)
    {
        this.place_name = place_name;
    }

    public String getPerson_Name ()
    {
        return Person_Name;
    }

    public void setPerson_Name (String Person_Name)
    {
        this.Person_Name = Person_Name;
    }

    public String getCityName ()
    {
        return CityName;
    }

    public void setCityName (String CityName)
    {
        this.CityName = CityName;
    }

    public String getStreetTital ()
    {
        return StreetTital;
    }

    public void setStreetTital (String StreetTital)
    {
        this.StreetTital = StreetTital;
    }

    public String getPerson_Id ()
    {
        return Person_Id;
    }

    public void setPerson_Id (String Person_Id)
    {
        this.Person_Id = Person_Id;
    }
}
