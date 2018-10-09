package com.darorman.gm3yaorman.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ahmed Mostafa on 9/3/2018.
 */
public class BaseDialog extends Dialog {

    private Unbinder unbinder;
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
    }
}
