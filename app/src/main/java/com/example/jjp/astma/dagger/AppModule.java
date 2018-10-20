package com.example.jjp.astma.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    private App app;

    AppModule(App application) {
        app = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}