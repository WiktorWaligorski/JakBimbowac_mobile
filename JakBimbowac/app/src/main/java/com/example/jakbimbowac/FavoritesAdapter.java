package com.example.jakbimbowac;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<FavoriteItem> items;

    public FavoritesAdapter(List<FavoriteItem> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == FavoriteItem.TYPE_HEADER) {

            View view = inflater.inflate(
                    R.layout.item_favorite_header,
                    parent,
                    false
            );

            return new HeaderVH(view);
        }

        if (viewType == FavoriteItem.TYPE_LINE) {

            View view = inflater.inflate(
                    R.layout.item_favorite_line,
                    parent,
                    false
            );

            return new LineVH(view);
        }

        View view = inflater.inflate(
                R.layout.item_favorite_stop,
                parent,
                false
        );

        return new StopVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        FavoriteItem item = items.get(position);

        if (holder instanceof HeaderVH) {

            ((HeaderVH) holder).title.setText(item.getTitle());
        }

        else if (holder instanceof LineVH) {

            ((LineVH) holder).title.setText(item.getTitle());
        }

        else if (holder instanceof StopVH) {

            ((StopVH) holder).title.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ================= VIEW HOLDERS =================

    static class HeaderVH extends RecyclerView.ViewHolder {

        TextView title;

        public HeaderVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.headerText);
        }
    }

    static class LineVH extends RecyclerView.ViewHolder {

        TextView title;

        public LineVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }

    static class StopVH extends RecyclerView.ViewHolder {

        TextView title;

        public StopVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.stopName);
        }
    }
}