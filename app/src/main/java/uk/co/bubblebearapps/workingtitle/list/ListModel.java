package uk.co.bubblebearapps.workingtitle.list;

import android.util.Log;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import uk.co.bubblebearapps.workingtitle.util.ListMappingUtils;
import uk.co.bubblebearapps.workingtitle.util.RxUtils;

@Singleton
public class ListModel implements ListContract.Model {
    private static final String TAG = ListModel.class.getSimpleName();

    private static final List<String> DUMMY_DATA = Lists.newArrayList(
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",
            "Aliquam tincidunt mauris eu risus.",
            "Vestibulum auctor dapibus neque.",
            "Nunc dignissim risus id metus.",
            "Cras ornare tristique elit.",
            "Vivamus vestibulum ntulla nec ante.",
            "Praesent placerat risus quis eros.",
            "Fusce pellentesque suscipit nibh.",
            "Integer vitae libero ac risus egestas placerat.",
            "Vestibulum commodo felis quis tortor.",
            "Ut aliquam sollicitudin leo.",
            "Cras iaculis ultricies nulla.",
            "Donec quis dui at dolor tempor interdum.");

    @Inject
    public ListModel() {
        Log.d(TAG, String.format("New instance: %s", this));
    }

    @Override
    public Single<List<ListItem>> getList() {
        Log.d(TAG, "getList()");
        return Single.just(DUMMY_DATA)
                .doOnSubscribe(RxUtils.NOT_ON_MAIN_THREAD_CONSUMER)
                .map(ListMappingUtils.getListMapper(ListItem::new))
                .delay(3, TimeUnit.SECONDS);
    }

    @Override
    public Completable doLongRunningOperation() {
        Log.d(TAG, "doLongRunningOperation()");
        return Completable.complete()
                .doOnSubscribe(RxUtils.NOT_ON_MAIN_THREAD_CONSUMER)
                .delay(3, TimeUnit.SECONDS);
    }
}
