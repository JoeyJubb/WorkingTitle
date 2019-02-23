package uk.co.bubblebearapps.workingtitle.di;

import dagger.Module;
import dagger.Provides;
import uk.co.bubblebearapps.workingtitle.list.ListAdapter;
import uk.co.bubblebearapps.workingtitle.list.ListContract;
import uk.co.bubblebearapps.workingtitle.list.ListFragment;
import uk.co.bubblebearapps.workingtitle.list.ListModel;
import uk.co.bubblebearapps.workingtitle.list.ListPresenter;

@Module
class ListFragmentModule {

    @Provides
    static ListContract.Model provideListModel(ListModel listModel) {
        return listModel;
    }

    @Provides
    static ListContract.View provideListView(ListFragment listFragment) {
        return listFragment.getListContractView();
    }

    @Provides
    static ListContract.Presenter provideListPresenter(ListPresenter presenter) {
        return presenter;
    }

    @Provides
    static ListAdapter.ActionHandler provideListActionHandler(ListFragment listFragment) {
        return listFragment;
    }
}
