package com.darorman.gm3yaorman.ui.banks_branches;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.banks.AccountNum;
import com.darorman.gm3yaorman.api.model.banks.Bankes;
import com.darorman.gm3yaorman.api.model.orman_activities.Activities;
import com.darorman.gm3yaorman.ui.orman_acitivities_details.OrmanActivitiesDetailsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Mostafa on 9/2/2018.
 */
public class BanksAdapter extends RecyclerView.Adapter<BanksAdapter.ViewHolder> {

    private List<Bankes> banks;
    LinearLayout detailsLayout;
    BanksBranchesFragment banksBranchesFragment;

    public BanksAdapter(List<Bankes> banks, LinearLayout detailsLayout, BanksBranchesFragment banksBranchesFragment) {
        this.banks = banks;
        this.detailsLayout = detailsLayout;
        this.banksBranchesFragment =banksBranchesFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.banks_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(banks.get(i), i);

    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.bank_name)
        TextView bankName;
//        @BindView(R.id.activities_image)
//        ImageView activityImage;

        private Context context;
        private Bundle bundle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bind(Bankes banks, int posotion){
            bankName.setText(banks.getBankTitel());
//            Glide.with(context)
//                    .load(activities.getActivityImgUrl())
//                    .into(activityImage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = concatenatePhones(banks.getAccountNum());

                    banksBranchesFragment.detailsLayout.setVisibility(View.VISIBLE);
                    banksBranchesFragment.branchName.setText("فرع " +banks.getBankeBranch());
                    banksBranchesFragment.bankPhone.setText(phone);
                    banksBranchesFragment.bankSoftCode.setText(banks.getSoftcode());
                }
            });
        }

        private String concatenatePhones(List<AccountNum> accountNum) {
            String phones="";
            for (int i=0; i<accountNum.size()-1; i++){
                phones+= accountNum.get(i).getAccountNum() +" - ";
            }
            phones += accountNum.get(accountNum.size()-1).getAccountNum();
            return phones;

        }
    }
}
