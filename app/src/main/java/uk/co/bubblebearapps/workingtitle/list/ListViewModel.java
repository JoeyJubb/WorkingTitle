package uk.co.bubblebearapps.workingtitle.list;

import android.util.Log;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import uk.co.bubblebearapps.workingtitle.util.Consumable;

public class ListViewModel extends ViewModel implements ListContract.View  {

    private static final String TAG = ListViewModel.class.getSimpleName();

    private final MutableLiveData<Boolean> mShowLoadingIndicator;

    private final MutableLiveData<List<ListItem>> mList;

    private final MutableLiveData<Throwable> mError;

    private final MutableLiveData<Consumable<String>> mItemDetailNav;

    public ListViewModel() {
        Log.d(TAG, String.format("New instance: %s", this));
        this.mShowLoadingIndicator = new MutableLiveData<>();
        this.mList = new MutableLiveData<>();
        this.mError = new MutableLiveData<>();
        this.mItemDetailNav = new MutableLiveData<>();
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        mShowLoadingIndicator.setValue(show);
    }

    @Override
    public void setError(@Nullable Throwable throwable) {
        mError.setValue(throwable);
    }

    @Override
    public void clearAll() {
        onCleared();
    }

    @Override
    public void showList(@Nullable List<ListItem> itemList) {
        mList.setValue(itemList);
    }

    @Override
    public void navigateToItemDetail(Consumable<String> title) { mItemDetailNav.setValue(title); }

    public LiveData<Boolean> getShowLoadingIndicator() {
        return mShowLoadingIndicator;
    }

    public LiveData<List<ListItem>> getList() {
        return mList;
    }

    public LiveData<Throwable> getError() {
        return mError;
    }

    public LiveData<Consumable<String>> getItemDetailNav() {
        return mItemDetailNav;
    }
}
