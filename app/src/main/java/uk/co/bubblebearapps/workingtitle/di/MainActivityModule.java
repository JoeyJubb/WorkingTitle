package uk.co.bubblebearapps.workingtitle.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import uk.co.bubblebearapps.workingtitle.MainActivity;
import uk.co.bubblebearapps.workingtitle.Navigator;
import uk.co.bubblebearapps.workingtitle.di.qualifier.ActivityContext;

@Module
public class MainActivityModule {

    @Provides
    @ActivityContext
    public static Context provideContext(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    public static Navigator navigator(MainActivity mainActivity) {
        return mainActivity;
    }
}
