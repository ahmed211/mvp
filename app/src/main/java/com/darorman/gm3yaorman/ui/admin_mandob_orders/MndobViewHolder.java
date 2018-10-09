package com.darorman.gm3yaorman.ui.admin_mandob_orders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.admin_mndob.TransactionDataResult;
import com.darorman.gm3yaorman.api.model.admin_mndob.UpdateOrderData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ahmed Mostafa on 9/19/2018.
 */
public class MndobViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.donation_value)
    TextView donationValue;
    @BindView(R.id.currency_type)
    TextView currencyType;
    @BindView(R.id.donation_type)
    TextView donationType;
    @BindView(R.id.check)
    ImageView check;
    private Context context;
    private AdminMndobOrdersPresenter presenter;
    private TransactionDataResult orderData;
    private int position;

    public MndobViewHolder(@NonNull View itemView, Context context, AdminMndobOrdersPresenter presenter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
        this.presenter = presenter;
    }

    public void bind(TransactionDataResult orderData, int pos){
        userName.setText(orderData.getPerson_Name());
        donationType.setText(orderData.getDonationtypename());
        donationValue.setText(orderData.getDon_Amount());
        currencyType.setText(orderData.getCur_Name());
        this.orderData = orderData;
        position = pos;
    }

    @OnClick(R.id.check)
    void check(View view){
        presenter.updateOrder(new UpdateOrderData(orderData.getTrans_Head_ID()), position);
//        check.setImageResource(R.drawable.checked);

    }
}
