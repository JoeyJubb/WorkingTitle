package uk.co.bubblebearapps.workingtitle.list;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class ListItem {

    @NonNull
    private final String title;

    public ListItem(@NonNull String title) {
        this.title = checkNotNull(title);
    }

    @NonNull
    public String getTitle() {
        return title;
    }
}
