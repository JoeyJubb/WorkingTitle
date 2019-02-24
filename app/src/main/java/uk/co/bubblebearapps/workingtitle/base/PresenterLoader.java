package uk.co.bubblebearapps.workingtitle.base;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.loader.content.Loader;

import javax.inject.Provider;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Loader to supply a mPresenter to an activty or fragment. Using a loader allows the mPresenter to be
 * retained during configuration changes
 *
 * @param <T> the mPresenter class this loader will deliver
 */
public class PresenterLoader<T extends BasePresenter> extends Loader<T> {

    private final Provider<T> mProvider;
    private T mPresenter;

    /**
     * @param context  used to retrieve the application strategy.
     * @param provider used to provide a mPresenter
     */
    public PresenterLoader(Context context, @NonNull Provider<T> provider) {
        super(context);
        this.mProvider = checkNotNull(provider);
    }

    // Constructor...

    @Override
    protected void onStartLoading() {

        // If we already own an instance, simply deliver it.
        if (mPresenter != null) {
            deliverResult(mPresenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        // Create the Presenter using the Provider
        mPresenter = mProvider.get();

        // Deliver the result
        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        mPresenter.onDestroyed();
        mPresenter = null;
    }

}