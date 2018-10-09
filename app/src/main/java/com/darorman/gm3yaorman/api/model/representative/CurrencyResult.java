package com.darorman.gm3yaorman.api.model.representative;

/**
 * Created by Ahmed Mostafa on 9/17/2018.
 */
public class CurrencyResult {
    private String Cur_ID;
    private String Cur_Name;

    public String getCur_ID ()
    {
        return Cur_ID;
    }

    public void setCur_ID (String Cur_ID)
    {
        this.Cur_ID = Cur_ID;
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
