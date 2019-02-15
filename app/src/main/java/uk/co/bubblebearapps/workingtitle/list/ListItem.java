package uk.co.bubblebearapps.workingtitle.list;

import static com.google.common.base.Preconditions.checkNotNull;

import androidx.annotation.NonNull;

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
