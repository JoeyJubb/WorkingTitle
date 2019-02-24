package uk.co.bubblebearapps.workingtitle.list;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import uk.co.bubblebearapps.workingtitle.base.UseCaseNoParams;
import uk.co.bubblebearapps.workingtitle.util.RxUtils;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class LongRunningUseCase implements UseCaseNoParams<Completable> {

    @Inject
    public LongRunningUseCase() {
        // empty for dagger
    }

    @Override
    public Completable get() {
        return Completable.complete()
                .delay(3, TimeUnit.SECONDS)
                .doOnSubscribe(RxUtils.NOT_ON_MAIN_THREAD_CONSUMER)
                .subscribeOn(Schedulers.newThread());
    }

}
