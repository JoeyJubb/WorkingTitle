package uk.co.bubblebearapps.workingtitle.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import javax.inject.Provider;

/**
 */
public abstract class BaseMvpFragment<P extends BasePresenter<V>, V extends BaseView> extends
        Fragment {

    private static final int LOADER_ID = 101;

    private BasePresenter<V> presenter;

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LoaderManager.getInstance(this)
                .initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
                    @NonNull
                    @Override
                    public final Loader<P> onCreateLoader(int id, Bundle args) {
                        return new PresenterLoader<>(getContext(), getPresenterProvider());
                    }

                    @Override
                    public final void onLoadFinished(@NonNull Loader<P> loader, P presenter) {
                        BaseMvpFragment.this.presenter = presenter;
                        onPresenterPrepared(presenter);
                    }

                    @Override
                    public final void onLoaderReset(@NonNull Loader<P> loader) {
                        BaseMvpFragment.this.presenter = null;
                        onPresenterDestroyed();
                    }
                });
    }

    /**
     * Use this method in case you want to specify a specific ID for the {@link PresenterLoader}. By
     * default its value would be {@link #LOADER_ID}.
     */
    @SuppressWarnings("WeakerAccess")
    protected int loaderId() {
        return LOADER_ID;
    }

    /**
     * Instance of {@link Provider} use to create a Presenter when needed. This instance
     * should not contain {@link android.app.Activity} strategy reference since it will be keep on
     * rotations.
     */
    @NonNull
    protected abstract Provider<P> getPresenterProvider();

    /**
     * Hook for subclasses that deliver the {@link BasePresenter}. Can be
     * use to initialize the Presenter or simple hold a reference to it.
     *
     * @param presenter the presenter controlling this view
     */
    protected abstract void onPresenterPrepared(@NonNull P presenter);

    /**
     * Hook for subclasses before the screen gets destroyed.
     */
    private void onPresenterDestroyed() {
        presenter.onDestroyed();
    }
}