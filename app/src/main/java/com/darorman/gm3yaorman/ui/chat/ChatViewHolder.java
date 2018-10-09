package com.darorman.gm3yaorman.ui.chat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.api.model.chat.GetChatResult;
import com.darorman.gm3yaorman.ui.orman_acitivities_details.OrmanActivitiesDetailsFragment;
import com.darorman.gm3yaorman.utilities.SharedPrefsHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Mostafa on 10/3/2018.
 */
public class ChatViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.message)
    TextView messageText;
    @BindView(R.id.chat_layout)
    LinearLayout chatLayout;

    private SharedPrefsHelper prefsHelper;

    public ChatViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        prefsHelper = new SharedPrefsHelper(context);

    }

    public void bind(GetChatResult message, int position){
        if ((prefsHelper.getAdminID() != null && message.getUserType() == "1") ||
                (prefsHelper.getUserID() != null && message.getUserType() == "0"))
            chatLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        else if((prefsHelper.getAdminID() != null && message.getUserType() == "0") ||
                (prefsHelper.getUserID() != null && message.getUserType() == "1"))
            chatLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        messageText.setText(message.getCMessage());
    }
}
