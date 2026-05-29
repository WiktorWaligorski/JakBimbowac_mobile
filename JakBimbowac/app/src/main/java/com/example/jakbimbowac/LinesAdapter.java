package com.example.jakbimbowac;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinesAdapter extends RecyclerView.Adapter<LinesAdapter.LineViewHolder> {

    private List<Line> lines;

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

        String firstStop = line.getRoute_forward().get(0);
        String lastStop = line.getRoute_forward().get(line.getRoute_forward().size() - 1);

        holder.lineDirection.setText(firstStop + " → " + lastStop);

        StringBuilder routeBuilder = new StringBuilder();

        for (int i = 0; i < line.getRoute_forward().size(); i++) {

            routeBuilder.append(line.getRoute_forward().get(i));

            if (i < line.getRoute_forward().size() - 1) {
                routeBuilder.append(" (10 min) → ");
            }
        }

        holder.lineRoute.setText(routeBuilder.toString());

        holder.bannerLayout.setBackgroundColor(Color.parseColor(line.getColor()));
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

        public LineViewHolder(@NonNull View itemView) {
            super(itemView);

            lineNumber = itemView.findViewById(R.id.lineNumber);
            lineDirection = itemView.findViewById(R.id.lineDirection);
            lineRoute = itemView.findViewById(R.id.lineRoute);
            bannerLayout = itemView.findViewById(R.id.bannerLayout);
        }
    }
}