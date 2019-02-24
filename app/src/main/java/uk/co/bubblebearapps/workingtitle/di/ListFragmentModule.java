package uk.co.bubblebearapps.workingtitle.di;

import dagger.Module;
import dagger.Provides;
import uk.co.bubblebearapps.workingtitle.di.qualifier.ListPresenterQuery;
import uk.co.bubblebearapps.workingtitle.list.*;

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

    @Provides
    @ListPresenterQuery
    static String provideListPresenterQuery(ListFragment listFragment) {
        return listFragment.getQuery();
    }
}
