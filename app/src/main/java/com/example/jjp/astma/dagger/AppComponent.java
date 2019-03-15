package com.example.jjp.astma.dagger;

import com.example.jjp.astma.modules.chart.ChartFragmentPresenter;
import com.example.jjp.astma.modules.login.LoginActivityPresenter;
import com.example.jjp.astma.modules.profile.ProfileFragmentPresenter;
import com.example.jjp.astma.modules.right.panel.RightPanelFragmentPresenter;
import com.example.jjp.astma.modules.top.panel.TopPanelFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent extends AndroidInjector<App> {

    void inject(RightPanelFragmentPresenter rightPanelFragmentPresenter);
    void inject(ChartFragmentPresenter chartFragmentPresenter);
    void inject(TopPanelFragmentPresenter topPanelFragmentPresenter);
    void inject(LoginActivityPresenter loginActivityPresenter);
    void inject(ProfileFragmentPresenter profileFragmentPresenter);

    final class Initializer {
        private Initializer() {
        }

        static AppComponent init(App app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
    }
}
