package com.darorman.gm3yaorman.api.model.representative;

/**
 * Created by Ahmed Mostafa on 9/17/2018.
 */
public class OrderData {
    private String Donation_Aim_ID;

    private String Don_type_ID;

    private String Currency_ID;

    private String Note;

    private String Don_Amount;

    private String Person_ID;

    public OrderData(String Donation_Aim_ID, String Don_type_ID, String Currency_ID,
                     String Note, String Don_Amount, String Person_ID) {
        this.Donation_Aim_ID = Donation_Aim_ID;
        this.Don_type_ID = Don_type_ID;
        this.Currency_ID = Currency_ID;
        this.Note = Note;
        this.Don_Amount = Don_Amount;
        this.Person_ID = Person_ID;
    }
}
