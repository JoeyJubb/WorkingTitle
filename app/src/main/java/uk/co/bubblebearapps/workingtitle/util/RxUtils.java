package uk.co.bubblebearapps.workingtitle.util;

import android.os.Looper;
import androidx.annotation.NonNull;
import io.reactivex.CompletableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import uk.co.bubblebearapps.workingtitle.base.BaseView;

public class RxUtils {

    public static final Consumer<Disposable> NOT_ON_MAIN_THREAD_CONSUMER = disposable -> {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new NotOnMainThreadException();
        }
    };

    private RxUtils() {
        // no instances
    }


    public static <T> SingleTransformer<T, T> getLoadingIndicatorSingleComposition(@NonNull final BaseView view) {
        return upstream -> upstream
                .doOnSubscribe(disposable -> view.showLoadingIndicator(true))
                .doOnEvent((t, throwable) -> view.showLoadingIndicator(false));
    }

    public static CompletableTransformer getLoadingIndicatorompleteableComposition(@NonNull final BaseView view) {
        return upstream -> upstream
                .doOnSubscribe(disposable -> view.showLoadingIndicator(true))
                .doOnComplete(() -> view.showLoadingIndicator(false));
    }

}
