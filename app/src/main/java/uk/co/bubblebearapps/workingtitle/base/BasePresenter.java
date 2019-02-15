package uk.co.bubblebearapps.workingtitle.base;

/**
 * Presenter interface
 */
public interface BasePresenter<V extends BaseView> {

    V getView();

    /**
     * Presenter is about to be given up to the garbage collector. Perform any clean up here
     */
    void onDestroyed();
}
