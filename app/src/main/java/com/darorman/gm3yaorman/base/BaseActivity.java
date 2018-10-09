package com.darorman.gm3yaorman.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ahmed Mostafa on 9/1/2018.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        initialization();
        initializeDagger();
        initializePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected abstract int getContentView();
    protected abstract void initializePresenter();
    protected abstract void initializeDagger();
    protected abstract void initialization();
}
