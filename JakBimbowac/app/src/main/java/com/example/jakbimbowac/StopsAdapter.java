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

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.StopViewHolder> {

    private final List<Stop> stops;
    private final List<Line> lines;

    public StopsAdapter(List<Stop> stops, List<Line> lines) {
        this.stops = stops;
        this.lines = lines;
    }

    @NonNull
    @Override
    public StopViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stop, parent, false);

        return new StopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull StopViewHolder holder,
            int position
    ) {

        Stop stop = stops.get(position);

        holder.stopName.setText(stop.getName());

        boolean favorite =
                FavoritesManager.isFavoriteStop(
                        holder.itemView.getContext(),
                        stop.getName()
                );

        holder.favoriteButton.setImageResource(
                favorite
                        ? android.R.drawable.btn_star_big_on
                        : android.R.drawable.btn_star_big_off
        );

        holder.favoriteButton.setOnClickListener(v -> {

            FavoritesManager.toggleStop(
                    holder.itemView.getContext(),
                    stop.getName()
            );

            notifyItemChanged(position);
        });

        holder.linesContainer.removeAllViews();

        for (Line line : lines) {

            if (line.getRoute_forward().contains(stop.getName())
                    || line.getRoute_backward().contains(stop.getName())) {

                View lineView =
                        LayoutInflater.from(
                                        holder.itemView.getContext())
                                .inflate(
                                        R.layout.item_stop_line,
                                        holder.linesContainer,
                                        false
                                );

                TextView lineButton =
                        lineView.findViewById(R.id.lineButton);

                LinearLayout departuresContainer =
                        lineView.findViewById(
                                R.id.departuresContainer
                        );

                lineButton.setText(
                        "Linia " + line.getNumber()
                );

                lineButton.setBackgroundColor(
                        Color.parseColor(line.getColor())
                );

                lineButton.setTextColor(Color.WHITE);

                List<String> departures =
                        line.getSchedule()
                                .get(stop.getName());

                if (departures != null) {

                    for (String departure : departures) {

                        TextView departureText =
                                new TextView(
                                        holder.itemView.getContext()
                                );

                        departureText.setText(
                                "Odjazd: " + departure
                        );

                        departureText.setTextSize(16);

                        departuresContainer.addView(
                                departureText
                        );
                    }
                }

                lineButton.setOnClickListener(v -> {

                    if (departuresContainer.getVisibility()
                            == View.GONE) {

                        departuresContainer.setVisibility(
                                View.VISIBLE
                        );

                    } else {

                        departuresContainer.setVisibility(
                                View.GONE
                        );
                    }
                });

                holder.linesContainer.addView(lineView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return stops.size();
    }

    public static class StopViewHolder
            extends RecyclerView.ViewHolder {

        TextView stopName;
        LinearLayout linesContainer;
        ImageButton favoriteButton;

        public StopViewHolder(@NonNull View itemView) {
            super(itemView);

            stopName =
                    itemView.findViewById(R.id.stopName);

            linesContainer =
                    itemView.findViewById(R.id.linesContainer);

            favoriteButton =
                    itemView.findViewById(R.id.favoriteButton);
        }
    }
}