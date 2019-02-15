package uk.co.bubblebearapps.workingtitle.list;

import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.Completable;
import io.reactivex.Single;
import uk.co.bubblebearapps.workingtitle.base.BasePresenter;
import uk.co.bubblebearapps.workingtitle.base.BaseView;
import uk.co.bubblebearapps.workingtitle.util.Consumable;

/**
 * Represents a contract between Model, View & Presenter
 */
public interface ListContract {

    interface Model {

        Single<List<ListItem>> getList();

        Completable doLongRunningOperation();
    }

    interface View extends BaseView {

        void showList(@Nullable List<ListItem> itemList);

        void navigateToItemDetail(Consumable<String> title);
    }

    interface Presenter extends BasePresenter<View> {

        void loadList();

        void showItemDetail(ListItem listItem);
    }
}
