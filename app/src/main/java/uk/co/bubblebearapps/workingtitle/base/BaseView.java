package uk.co.bubblebearapps.workingtitle.base;

import androidx.annotation.Nullable;

public interface BaseView {

    void showLoadingIndicator(boolean show);

    void setError(@Nullable Throwable throwable);

    void clearAll();
}
