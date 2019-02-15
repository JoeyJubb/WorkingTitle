package uk.co.bubblebearapps.workingtitle.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import uk.co.bubblebearapps.workingtitle.list.ListFragment;

@Subcomponent(modules = ListFragmentModule.class)
public interface ListFragmentSubcomponent extends AndroidInjector<ListFragment>{

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<ListFragment> {
    }
}
