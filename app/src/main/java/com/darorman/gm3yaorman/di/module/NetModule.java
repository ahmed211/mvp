package com.darorman.gm3yaorman.di.module;

import com.darorman.gm3yaorman.api.ClientApiServies;
import com.darorman.gm3yaorman.usecase.UsecaseImpl;
import com.darorman.gm3yaorman.usecase.Usecase;
import com.darorman.gm3yaorman.utilities.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private Retrofit retrofit;
    private ClientApiServies apiServies;
    private OkHttpClient okHttpClient;

    @Singleton
    @Provides
    Usecase proideUsecase(){
        return new UsecaseImpl(provideApiService());
    }

    @Singleton
    @Provides
    ClientApiServies provideApiService() {
        if (apiServies == null){
            apiServies = provieRetofite().create(ClientApiServies.class);
        }
        return apiServies;
    }

    @Singleton
    @Provides
    Retrofit provieRetofite() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(provieOkHttpClient())
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Singleton
    @Provides
    OkHttpClient provieOkHttpClient() {
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }
}
