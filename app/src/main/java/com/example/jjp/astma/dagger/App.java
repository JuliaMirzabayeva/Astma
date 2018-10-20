package com.example.jjp.astma.dagger;

import android.app.Application;

public class App extends Application {

    private static AppComponent appComponent;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        buildComponentGraph();
    }

    public static AppComponent component() {
        return appComponent;
    }

    public static void buildComponentGraph() {
        appComponent = AppComponent.Initializer.init(app);
    }
}
