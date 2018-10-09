package com.darorman.gm3yaorman.ui.create_account.verify_mobile;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.admin_mndob.TransactionDataResult;
import com.darorman.gm3yaorman.api.model.verify_mobile.GetDATAResult;
import com.darorman.gm3yaorman.ui.admin_mandob_orders.AdminMndobOrdersPresenter;
import com.darorman.gm3yaorman.ui.admin_mandob_orders.MndobViewHolder;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/19/2018.
 */
public class VerifyUserAdapter extends RecyclerView.Adapter<VerifyViewHolder> {

    private List<GetDATAResult> usersData;
    private String mobile;

    public VerifyUserAdapter(List<GetDATAResult> usersData, String mobile) {
        this.usersData = usersData;
        this.mobile = mobile;
    }

    @NonNull
    @Override
    public VerifyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.verify_user_item, viewGroup, false);
        return new VerifyViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull VerifyViewHolder verifyViewHolder, int i) {
        verifyViewHolder.bind(usersData.get(i), i, mobile);
    }

    @Override
    public int getItemCount() {
        return usersData.size();
    }
}
