package uk.co.bubblebearapps.workingtitle.di;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import uk.co.bubblebearapps.workingtitle.MainActivity;

@Module(subcomponents = MainActivitySubcomponent.class)
public abstract class MainActivityBuilderModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity.class)
    abstract AndroidInjector.Factory<?> bindMainActivityInjectorFactory(MainActivitySubcomponent.Builder builder);

}
