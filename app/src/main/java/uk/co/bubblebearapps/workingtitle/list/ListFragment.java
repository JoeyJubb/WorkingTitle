package uk.co.bubblebearapps.workingtitle.list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.base.Optional;
import dagger.android.support.AndroidSupportInjection;
import uk.co.bubblebearapps.workingtitle.Navigator;
import uk.co.bubblebearapps.workingtitle.R;
import uk.co.bubblebearapps.workingtitle.base.BaseMvpFragment;
import uk.co.bubblebearapps.workingtitle.list.ListContract.Presenter;
import uk.co.bubblebearapps.workingtitle.util.Consumable;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 */
public class ListFragment extends
        BaseMvpFragment<ListContract.Presenter, ListContract.View> implements ListAdapter.ActionHandler {

    private static final String TAG = ListFragment.class.getSimpleName();
    private static final String ARG_QUERY = "ARG_QUERY";

    @Inject Navigator mNavigator;
    @Inject Provider<Presenter> mPresenterProvider;
    @Inject ListAdapter mListAdapter;

    private ListViewModel mListViewModel;
    private Presenter mPresenter;

    private ContentLoadingProgressBar mProgressBar;
    private Snackbar mSnackBar;

    public static ListFragment newInstance(@NonNull String query) {
        final Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);

        final ListFragment listFragment = new ListFragment();
        listFragment.setArguments(args);
        return listFragment;
    }

    public ListFragment() {
        Log.d(TAG, String.format("New instance: %s", this));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListViewModel = ViewModelProviders.of(requireActivity()).get(ListViewModel.class);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mListAdapter);

        mProgressBar = view.findViewById(R.id.loading_indicator);

        mListViewModel.getShowLoadingIndicator().observe(this, this::showLoadingIndicator);
        mListViewModel.getError().observe(this, this::setError);
        mListViewModel.getItemDetailNav().observe(this, this::navigateToItemDetail);
        mListViewModel.getList().observe(this, mListAdapter::setList);
    }

    @NonNull
    public ListContract.View getListContractView() {
        return mListViewModel;
    }

    @NonNull
    @Override
    protected Provider<Presenter> getPresenterProvider() {
        return mPresenterProvider;
    }

    @Override
    protected void onPresenterPrepared(@NonNull Presenter presenter) {
        Log.d(TAG, String.format("onPresenterPrepared(%s)", presenter));
        mPresenter = presenter;
    }

    public String getQuery() {
        return checkNotNull(getArguments()).getString(ARG_QUERY);
    }

    private void navigateToItemDetail(Consumable<String> title) {
        final Optional<String> consume = title.consume();
        if (consume.isPresent()) {
            mNavigator.navigateToItemDetail();
        }
    }

    private void showLoadingIndicator(boolean show) {
        if (show) {
            mProgressBar.show();
        } else {
            mProgressBar.hide();
        }
    }

    private void setError(@Nullable Throwable throwable) {
        if (mSnackBar != null) {
            mSnackBar.dismiss();
            mSnackBar = null;
        }

        if (throwable != null && getView() != null) {
            mSnackBar = Snackbar.make(getView(), throwable.getMessage(), Snackbar.LENGTH_INDEFINITE);
            mSnackBar.show();
        }
    }

    @Override
    public void onListItemClick(ListItem listItem) {
        if (!mProgressBar.isShown()) {
            mPresenter.showItemDetail(listItem);
        }
    }
}
