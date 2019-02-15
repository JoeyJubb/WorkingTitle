package uk.co.bubblebearapps.workingtitle.di;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;
import uk.co.bubblebearapps.workingtitle.Application;

@Singleton
@Component(modules = {
    AndroidSupportInjectionModule.class,
    AppModule.class,
    MainActivityBuilderModule.class})
public interface AppComponent {

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }

  void inject(Application app);
}