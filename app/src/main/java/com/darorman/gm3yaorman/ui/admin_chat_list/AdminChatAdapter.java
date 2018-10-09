package com.darorman.gm3yaorman.ui.admin_chat_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.admin_chat.AdminChatData;
import com.darorman.gm3yaorman.api.model.admin_chat.GetListChatResult;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/30/2018.
 */
public class AdminChatAdapter extends RecyclerView.Adapter<AdminChatViewHolder> {
    private List<GetListChatResult> chatData;
    private Context context;
    public AdminChatAdapter(List<GetListChatResult> chatData) {
        this.chatData = chatData;
    }

    @NonNull
    @Override
    public AdminChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admin_chat_item, viewGroup, false);
        context = viewGroup.getContext();
        return new AdminChatViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminChatViewHolder adminChatViewHolder, int i) {
        adminChatViewHolder.bind(chatData.get(i), i);
    }

    @Override
    public int getItemCount() {
        return chatData.size();
    }
}
