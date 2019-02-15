package uk.co.bubblebearapps.workingtitle.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import uk.co.bubblebearapps.workingtitle.Application;
import uk.co.bubblebearapps.workingtitle.di.qualifier.ApplicationContext;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public class AppModule {

    @Provides
    @ApplicationContext
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }
}