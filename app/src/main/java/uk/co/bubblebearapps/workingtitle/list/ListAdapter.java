package uk.co.bubblebearapps.workingtitle.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.bubblebearapps.workingtitle.R;
import uk.co.bubblebearapps.workingtitle.di.qualifier.ActivityContext;

public class ListAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    private static final String TAG = ListAdapter.class.getSimpleName();

    @NonNull private final LayoutInflater mLayoutInflater;
    @NonNull private final ActionHandler mActionHandler;

    @Nullable private List<ListItem> itemList;

    @Inject
    public ListAdapter(@ActivityContext @NonNull Context context,
            @NonNull ActionHandler actionHandler) {

        Log.d(TAG, String.format("New instance: %s", this));
        mLayoutInflater = LayoutInflater.from(context);
        this.mActionHandler = actionHandler;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemViewHolder(
                mLayoutInflater.inflate(R.layout.item_viewitem, parent, false),
                mActionHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        holder.bind(getItemAt(position));
    }

    @Nullable
    private ListItem getItemAt(int position) {
        return itemList != null ? itemList.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public void setList(List<ListItem> itemList) {
        Log.d(TAG, String.format("setList(%s)", itemList));
        this.itemList = itemList;
        notifyDataSetChanged();
    }



    interface ActionHandler {
        void onListItemClick(ListItem listItem);
    }
}
