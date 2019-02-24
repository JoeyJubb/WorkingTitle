package uk.co.bubblebearapps.workingtitle.list;

import android.util.Log;
import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import uk.co.bubblebearapps.workingtitle.di.qualifier.ListPresenterQuery;
import uk.co.bubblebearapps.workingtitle.util.Consumable;
import uk.co.bubblebearapps.workingtitle.util.RxUtils;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class ListPresenter implements ListContract.Presenter {

    private static final String TAG = ListPresenter.class.getSimpleName();

    @NonNull private final ListContract.Model mModel;
    @NonNull private final ListContract.View mView;
    @NonNull
    private final String mQuery;

    @NonNull private final CompositeDisposable mCompositeDisposable;

    @Inject
    public ListPresenter(
            @NonNull ListContract.Model model,
            @NonNull ListContract.View view,
            @NonNull @ListPresenterQuery String query
    ) {
        Log.d(TAG, String.format("New instance: %s", this));

        mModel = checkNotNull(model);
        mView = checkNotNull(view);
        mQuery = checkNotNull(query);

        mCompositeDisposable = new CompositeDisposable();

        loadList();
    }

    @Override
    public void loadList() {
        final Disposable subscribe = mModel.getList(mQuery)
                .compose(RxUtils.getLoadingIndicatorSingleComposition(mView))
                .doOnSubscribe(disposable -> mView.setError(null))
                .subscribe(mView::showList, mView::setError);
        mCompositeDisposable.add(subscribe);
    }

    @Override
    public void showItemDetail(ListItem listItem) {
        final Disposable subscribe = mModel.doLongRunningOperation()
                .compose(RxUtils.getLoadingIndicatorompleteableComposition(mView))
                .doOnSubscribe(disposable -> mView.setError(null))
                .subscribe(
                        () -> mView.navigateToItemDetail(new Consumable<>(listItem.getTitle())),
                        mView::setError);
        mCompositeDisposable.add(subscribe);
    }

    @Override
    public ListContract.View getView() {
        return mView;
    }

    @Override
    public void onDestroyed() {
        mView.clearAll();
        mCompositeDisposable.clear();
    }
}
