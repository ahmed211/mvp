package com.darorman.gm3yaorman.application;

import android.app.Application;
import android.content.Context;

import com.darorman.gm3yaorman.di.DaggerNetComponent;
import com.darorman.gm3yaorman.di.NetComponent;
import com.darorman.gm3yaorman.di.module.AppModule;
import com.darorman.gm3yaorman.di.module.NetModule;

import java.lang.ref.WeakReference;

public class App extends Application {

    private static Context context;
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }


    public static Context getContext() {
        return context;
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
