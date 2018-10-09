package com.darorman.gm3yaorman.base;

/**
 * Created by Ahmed Mostafa on 9/1/2018.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);
    void detachView();
}
