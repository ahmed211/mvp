package com.darorman.gm3yaorman.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ahmed Mostafa on 9/1/2018.
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V view;
    private CompositeDisposable disposable = new CompositeDisposable();
    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        disposable.clear();
        disposable.dispose();
    }

    public V getView() {
        return view;
    }

    public void checkViewAttached(){
        if (!isViewAttached())
            throw new MvpViewNoAttachedException();
    }

    private boolean isViewAttached() {
        return view != null;
    }

    public void addDisposal(Disposable disposable){
        this.disposable.add(disposable);
    }

    public static class MvpViewNoAttachedException extends RuntimeException{
        public MvpViewNoAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter");
        }
    }
}
