package com.example.jakbimbowac;

import android.content.Context;
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

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.StopViewHolder> {

    private List<Stop> stops;
    private List<Line> lines;

    public StopsAdapter(List<Stop> stops, List<Line> lines) {
        this.stops = stops;
        this.lines = lines;
    }

    @NonNull
    @Override
    public StopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stop, parent, false);

        return new StopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StopViewHolder holder, int position) {

        Stop stop = stops.get(position);
        Context context = holder.itemView.getContext();

        holder.stopName.setText(stop.getName());

        // ⭐ FAVORITES STATE
        boolean favorite = FavoritesManager.isFavoriteStop(context, stop.getName());

        holder.favoriteButton.setImageResource(
                favorite
                        ? android.R.drawable.btn_star_big_on
                        : android.R.drawable.btn_star_big_off
        );

        holder.favoriteButton.setOnClickListener(v -> {

            FavoritesManager.toggleStop(context, stop.getName());

            notifyItemChanged(holder.getAdapterPosition());
        });

        // CLEAR OLD VIEWS
        holder.linesContainer.removeAllViews();

        for (Line line : lines) {

            boolean contains =
                    line.getRoute_forward().contains(stop.getName())
                            || line.getRoute_backward().contains(stop.getName());

            if (!contains) continue;

            View lineView = LayoutInflater.from(context)
                    .inflate(R.layout.item_stop_line, holder.linesContainer, false);

            TextView lineButton = lineView.findViewById(R.id.lineButton);
            LinearLayout departuresContainer = lineView.findViewById(R.id.departuresContainer);

            lineButton.setText("Linia " + line.getNumber());
            lineButton.setBackgroundColor(Color.parseColor(line.getColor()));
            lineButton.setTextColor(Color.WHITE);

            List<String> departures = line.getSchedule().get(stop.getName());

            if (departures != null) {
                for (String d : departures) {
                    TextView tv = new TextView(context);
                    tv.setText("Odjazd: " + d);
                    departuresContainer.addView(tv);
                }
            }

            lineButton.setOnClickListener(v -> {
                departuresContainer.setVisibility(
                        departuresContainer.getVisibility() == View.VISIBLE
                                ? View.GONE
                                : View.VISIBLE
                );
            });

            holder.linesContainer.addView(lineView);
        }
    }

    @Override
    public int getItemCount() {
        return stops.size();
    }

    public static class StopViewHolder extends RecyclerView.ViewHolder {

        TextView stopName;
        ImageButton favoriteButton;
        LinearLayout linesContainer;

        public StopViewHolder(@NonNull View itemView) {
            super(itemView);

            stopName = itemView.findViewById(R.id.stopName);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
            linesContainer = itemView.findViewById(R.id.linesContainer);
        }
    }
}