package com.darorman.gm3yaorman.api.model.banks;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/5/2018.
 */
public class BankesResult {
    @SerializedName("Bankes")
    private List<Bankes> bankes;

    public List<Bankes> getBankes() {
        return bankes;
    }
}
