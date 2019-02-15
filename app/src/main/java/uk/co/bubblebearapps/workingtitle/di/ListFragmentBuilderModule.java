package uk.co.bubblebearapps.workingtitle.di;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import uk.co.bubblebearapps.workingtitle.list.ListFragment;

@Module(subcomponents = ListFragmentSubcomponent.class)
public abstract class ListFragmentBuilderModule {

    @Binds
    @IntoMap
    @ClassKey(ListFragment.class)
    abstract AndroidInjector.Factory<?>
    bindListFragmentInjectorFactory(ListFragmentSubcomponent.Builder builder);
}
