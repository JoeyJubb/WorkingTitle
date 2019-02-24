package uk.co.bubblebearapps.workingtitle.base;

import androidx.annotation.NonNull;

public interface UseCase<P, T> {

    T get(@NonNull P params);

}
