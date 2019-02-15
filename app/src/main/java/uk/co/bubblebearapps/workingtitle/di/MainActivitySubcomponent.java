package uk.co.bubblebearapps.workingtitle.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import uk.co.bubblebearapps.workingtitle.MainActivity;

@Subcomponent(modules = {
        MainActivityModule.class,
        ListFragmentBuilderModule.class
})
public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }
}
