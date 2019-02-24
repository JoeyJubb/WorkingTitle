package uk.co.bubblebearapps.workingtitle.util;

import androidx.annotation.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ListMappingUtils {

    private ListMappingUtils() {
        // No instances allowed
    }

    /**
     * @param <T> Type for objects in source list
     * @param <R> Type for objects in resulting list
     */
    @NonNull
    public static <T, R> Function<List<T>, List<R>> getListMapper(@NonNull final Function<T, R> singleFunction) {
        return sourceList -> {
            checkNotNull(sourceList);

            final List<R> destinationList = new ArrayList<>(sourceList.size());
            for (T sourceListItem : sourceList) {
                destinationList.add(singleFunction.apply(sourceListItem));
            }
            return destinationList;
        };
    }

    /**
     * @param <T> Type for objects in source list
     * @param <U> Type for single object to map with source list
     * @param <R> Type for objects in resulting list
     */
    @NonNull
    public static <T, U, R> BiFunction<List<T>, U, List<R>> getListMapper(
            @NonNull final BiFunction<T, U, R> singleFunction) {
        return (soruceList, sourceItem) -> {
            checkNotNull(soruceList);

            final List<R> destinationList = new ArrayList<>(soruceList.size());
            for (T sourceListItem : soruceList) {
                destinationList.add(singleFunction.apply(sourceListItem, sourceItem));
            }
            return destinationList;
        };
    }
}
