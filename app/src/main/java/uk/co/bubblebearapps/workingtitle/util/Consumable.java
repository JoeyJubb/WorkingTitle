package uk.co.bubblebearapps.workingtitle.util;

import com.google.common.base.Optional;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Consumable<T> {

    private final T mItem;
    private boolean mConsumed;

    public Consumable(@NonNull T item) {
        mItem = checkNotNull(item);
        mConsumed = false;
    }

    public Optional<T> consume() {
        final Optional<T> result;
        if (!mConsumed) {
            result = Optional.of(mItem);
        } else {
            result = Optional.absent();
        }
        mConsumed = true;
        return result;
    }
}
