package com.example.jakbimbowac;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinesAdapter extends RecyclerView.Adapter<LinesAdapter.LineViewHolder> {

    private final List<Line> lines;

    public LinesAdapter(List<Line> lines) {
        this.lines = lines;
    }

    @NonNull
    @Override
    public LineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_line, parent, false);

        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolder holder, int position) {

        Line line = lines.get(position);

        holder.lineNumber.setText(line.getNumber());

        List<String> route = line.getRoute_forward();

        if (route == null || route.isEmpty()) return;

        String firstStop = route.get(0);
        String lastStop = route.get(route.size() - 1);

        holder.lineDirection.setText(firstStop + " → " + lastStop);

        StringBuilder routeBuilder = new StringBuilder();

        for (int i = 0; i < route.size(); i++) {

            routeBuilder.append(route.get(i));

            if (i < route.size() - 1) {
                routeBuilder.append(" (10 min) → ");
            }
        }

        holder.lineRoute.setText(routeBuilder.toString());

        holder.bannerLayout.setBackgroundColor(
                android.graphics.Color.parseColor(line.getColor())
        );

        // ⭐ FAVORITE ICON
        boolean isFav = FavoritesManager.isFavoriteLine(holder.itemView.getContext(), line.getNumber());

        holder.favoriteButton.setImageResource(
                isFav
                        ? android.R.drawable.btn_star_big_on
                        : android.R.drawable.btn_star_big_off
        );

        holder.favoriteButton.setOnClickListener(v -> {

            boolean currentlyFav = FavoritesManager.isFavoriteLine(holder.itemView.getContext(), line.getNumber());

            if (currentlyFav) {
                FavoritesManager.removeLine(holder.itemView.getContext(), line.getNumber());
                holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
            } else {
                FavoritesManager.addLine(holder.itemView.getContext(), line.getNumber());
                holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    public static class LineViewHolder extends RecyclerView.ViewHolder {

        TextView lineNumber;
        TextView lineDirection;
        TextView lineRoute;
        LinearLayout bannerLayout;
        ImageButton favoriteButton;

        public LineViewHolder(@NonNull View itemView) {
            super(itemView);

            lineNumber = itemView.findViewById(R.id.lineNumber);
            lineDirection = itemView.findViewById(R.id.lineDirection);
            lineRoute = itemView.findViewById(R.id.lineRoute);
            bannerLayout = itemView.findViewById(R.id.bannerLayout);

            // 🔥 TO JEST BRAKUJĄCA LINIA
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }
}