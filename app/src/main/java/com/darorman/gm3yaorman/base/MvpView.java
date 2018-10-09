package com.darorman.gm3yaorman.base;

/**
 * Created by Ahmed Mostafa on 9/1/2018.
 */
public interface MvpView {
    void showError(String message);
    void showDialog();
    void dismissDialog();
}
