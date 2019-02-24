package uk.co.bubblebearapps.workingtitle;

import android.app.Activity;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import uk.co.bubblebearapps.workingtitle.di.DaggerAppComponent;

import javax.inject.Inject;

public class Application extends android.app.Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }
}
