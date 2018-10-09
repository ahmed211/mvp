package com.darorman.gm3yaorman.ui.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.chat.GetChatResult;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 10/3/2018.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private List<GetChatResult> messages;

    public ChatAdapter(List<GetChatResult> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item, viewGroup, false);
        return new ChatViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {
        chatViewHolder.bind(messages.get(i), i);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
