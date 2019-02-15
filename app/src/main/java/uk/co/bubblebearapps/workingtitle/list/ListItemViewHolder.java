package uk.co.bubblebearapps.workingtitle.list;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.bubblebearapps.workingtitle.R;

import static com.google.common.base.Preconditions.checkNotNull;

class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @NonNull private final ListAdapter.ActionHandler mActionHandler;
    @NonNull private final TextView mTextView;

    @Nullable private ListItem mListItem;

    public ListItemViewHolder(@NonNull View itemView, @NonNull ListAdapter.ActionHandler actionHandler) {
        super(itemView);
        mTextView = checkNotNull(itemView.findViewById(R.id.text1));
        this.mActionHandler = actionHandler;
        mTextView.setOnClickListener(this);
    }

    public void bind(@Nullable ListItem listItem) {
        mListItem = listItem;
        if (listItem != null) {
            mTextView.setText(listItem.getTitle());
        } else {
            mTextView.setText(null);
        }
    }

    @Override
    public void onClick(View v) {
        mActionHandler.onListItemClick(mListItem);
    }
}
