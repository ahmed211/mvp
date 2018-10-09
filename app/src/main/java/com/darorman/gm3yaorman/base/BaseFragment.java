package com.darorman.gm3yaorman.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ahmed Mostafa on 9/1/2018.
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    public ProgressDialog progressDialog;
    public View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initialization();
        initializeDagger();
        intializePresenter();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }



    @Nullable
    @Override
    public View getView() {
        return view;
    }

    protected abstract void initializeDagger();
    protected abstract void intializePresenter();
    protected abstract int getLayoutId();
    protected abstract void initialization();

}
