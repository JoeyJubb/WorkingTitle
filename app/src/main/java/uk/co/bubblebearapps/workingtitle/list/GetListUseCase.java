package uk.co.bubblebearapps.workingtitle.list;

import com.google.common.collect.Lists;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import uk.co.bubblebearapps.workingtitle.base.UseCase;
import uk.co.bubblebearapps.workingtitle.util.RxUtils;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetListUseCase implements UseCase<String, Single<List<String>>> {

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
    public GetListUseCase() {
        // empty for dagger
    }

    @Override
    public Single<List<String>> get(@Nullable String query) {
        return Single.just(DUMMY_DATA)
                .delay(3, TimeUnit.SECONDS)
                .doOnSubscribe(RxUtils.NOT_ON_MAIN_THREAD_CONSUMER)
                .subscribeOn(Schedulers.newThread());
    }

}
