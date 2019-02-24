package uk.co.bubblebearapps.workingtitle.list;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import uk.co.bubblebearapps.workingtitle.util.ListMappingUtils;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ListModel implements ListContract.Model {

    @NonNull
    private GetListUseCase getListUseCase;
    @NonNull
    private LongRunningUseCase longRunningUseCase;

    @Inject
    public ListModel(@NonNull GetListUseCase getListUseCase,
                     @NonNull LongRunningUseCase longRunningUseCase) {
        this.getListUseCase = checkNotNull(getListUseCase);
        this.longRunningUseCase = checkNotNull(longRunningUseCase);
    }

    @Override
    public Single<List<ListItem>> getList(@NonNull String query) {
        return getListUseCase.get(query)
                .observeOn(Schedulers.computation())
                .map(ListMappingUtils.getListMapper(ListItem::new))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable doLongRunningOperation() {
        return longRunningUseCase.get()
                .observeOn(AndroidSchedulers.mainThread());
    }
}
