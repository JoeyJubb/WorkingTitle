package uk.co.bubblebearapps.workingtitle.di;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import uk.co.bubblebearapps.workingtitle.Application;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        MainActivityBuilderModule.class})
public interface AppComponent {

    void inject(Application app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}