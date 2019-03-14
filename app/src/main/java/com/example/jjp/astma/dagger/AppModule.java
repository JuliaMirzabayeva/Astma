package com.example.jjp.astma.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.jjp.astma.api.ApiService;
import com.example.jjp.astma.models.QuotesManager;
import com.example.jjp.astma.preferences.CommonPreferencesHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class AppModule {
    private static final String API_URL = "http://91.151.187.12:5000"; //http://172.18.13.68:5000"; //"http://differum-001-site1.htempurl.com"
    private App app;

    AppModule(App application) {
        app = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiService.class);
    }

    @Provides
    Context provideContext() {
        return app;
    }

    @Provides
    SharedPreferences providePreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    CommonPreferencesHelper provideCommonPreferencesHelper(SharedPreferences preferences){
        return new CommonPreferencesHelper(preferences);
    }

    @Provides
    @Singleton
    QuotesManager provideQuotesManager(){
        return new QuotesManager();
    }
}