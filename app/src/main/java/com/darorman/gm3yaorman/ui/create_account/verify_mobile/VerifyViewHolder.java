package com.darorman.gm3yaorman.ui.create_account.verify_mobile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.admin_mndob.TransactionDataResult;
import com.darorman.gm3yaorman.api.model.admin_mndob.UpdateOrderData;
import com.darorman.gm3yaorman.api.model.verify_mobile.GetDATAResult;
import com.darorman.gm3yaorman.ui.admin_mandob_orders.AdminMndobOrdersPresenter;
import com.darorman.gm3yaorman.ui.create_account.signup_3.SignUp3Fragment;
import com.darorman.gm3yaorman.ui.create_account.verify_donation.VerifyDonationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ahmed Mostafa on 9/19/2018.
 */
public class VerifyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.place_name)
    TextView placeName;
    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.street_title)
    TextView streetTitle;
    private View itemView;
    private Context context;
    private Bundle bundle;
    private VerifyDonationFragment donationFragment;
    public VerifyViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
        this.context = context;
        donationFragment = new VerifyDonationFragment();
        bundle = new Bundle();
    }

    public void bind(GetDATAResult userData, int pos, String mobile){
        userName.setText(userData.getPerson_Name());
        placeName.setText(userData.getPlace_name());
        cityName.setText(userData.getCityName());
        streetTitle.setText(userData.getStreetTital());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("mobile", mobile);
                bundle.putString("user_id", userData.getPerson_Id());
                donationFragment.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, donationFragment).commit();
            }
        });
    }


}
