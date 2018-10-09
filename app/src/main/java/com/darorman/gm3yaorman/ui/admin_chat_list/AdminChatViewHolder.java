package com.darorman.gm3yaorman.ui.admin_chat_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.admin_chat.GetListChatResult;
import com.darorman.gm3yaorman.ui.admin_main.AdminActivity;
import com.darorman.gm3yaorman.ui.chat.ChatFragment;
import com.darorman.gm3yaorman.ui.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Mostafa on 9/30/2018.
 */
public class AdminChatViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.chat_name)
    TextView name;
    private View itemView;
    private Bundle bundle;
    private ChatFragment chatFragment;
    private FragmentManager fragmentManager;
    private Context context;

    public AdminChatViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
        this.context = context;
        bundle = new Bundle();
        chatFragment = new ChatFragment();
        fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();

    }

    public void bind(GetListChatResult data, int position){
        name.setText(data.getUserName());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("user_id", data.getUserId());
                chatFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.admin_layouts, chatFragment).commit();
                ((AdminActivity)context).updateTileName("المحادثة");

            }
        });
    }
}
