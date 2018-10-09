package com.darorman.gm3yaorman.ui.orman_branches;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.orman_branches.GetDepInfoResult;
import com.darorman.gm3yaorman.base.listeners.RecyclerIemLisener;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Mostafa on 9/2/2018.
 */
public class OrmanBranchesAdapter extends RecyclerView.Adapter<OrmanBranchesAdapter.ViewHolder> {

    private List<GetDepInfoResult> branchesData;
    private RecyclerIemLisener iemLisener;

    public OrmanBranchesAdapter(List<GetDepInfoResult> branchesData, RecyclerIemLisener iemLisener) {
        this.branchesData = branchesData;
        this.iemLisener = iemLisener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.orman_branches_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(branchesData.get(i),iemLisener , i);
    }

    @Override
    public int getItemCount() {
        return branchesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.branch_name)
        TextView branchName;
        @BindView(R.id.branch_item)
        LinearLayout branchItem;
        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bind(GetDepInfoResult branchData, RecyclerIemLisener recyclerIemLisener, int posotion){
            branchName.setText(branchData.getOfficeName());
            branchItem.setOnClickListener(view -> recyclerIemLisener.onItemSelected(posotion));
        }
    }
}
