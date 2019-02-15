package uk.co.bubblebearapps.workingtitle.util;

import android.os.Looper;

import androidx.annotation.NonNull;
import io.reactivex.CompletableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import uk.co.bubblebearapps.workingtitle.base.BaseView;

public class RxUtils {

    private static final CompletableTransformer IO_COMPOSITION_COMPLETABLE = upstream -> upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    public static final Consumer<Disposable> NOT_ON_MAIN_THREAD_CONSUMER = disposable -> {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new NotOnMainThreadException();
        }
    };

    private RxUtils() {
        // no instances
    }

    public static <T> SingleTransformer<T, T> getIoSchedulerSingleComposition() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SingleTransformer<T, T> getLoadingIndicatorSingleComposition(@NonNull final BaseView view) {
        return upstream -> upstream
                .doOnSubscribe(disposable -> view.showLoadingIndicator(true))
                .doOnEvent((t, throwable) -> view.showLoadingIndicator(false));
    }

    public static CompletableTransformer getIoSchedulerCompleteableComposition() {
        return IO_COMPOSITION_COMPLETABLE;
    }

    public static CompletableTransformer getLoadingIndicatorompleteableComposition(@NonNull final BaseView view) {
        return upstream -> upstream
                .doOnSubscribe(disposable -> view.showLoadingIndicator(true))
                .doOnComplete(() -> view.showLoadingIndicator(false));
    }
}
