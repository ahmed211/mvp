package com.darorman.gm3yaorman.ui.admin_mandob_orders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.admin_mndob.TransactionDataResult;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/19/2018.
 */
public class MndobOrdersAdapter extends RecyclerView.Adapter<MndobViewHolder> {

    private List<TransactionDataResult> mndobData;
    private AdminMndobOrdersPresenter presenter;
    public MndobOrdersAdapter(List<TransactionDataResult> mndobData, AdminMndobOrdersPresenter presenter) {
        this.mndobData = mndobData;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MndobViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mandob_item, viewGroup, false);
        return new MndobViewHolder(view, viewGroup.getContext(), presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull MndobViewHolder mndobViewHolder, int i) {
        mndobViewHolder.bind(mndobData.get(i), i);
    }

    @Override
    public int getItemCount() {
        return mndobData.size();
    }
}
