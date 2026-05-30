package com.example.jakbimbowac;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {

    private List<RouteStep> steps;

    public RouteAdapter(List<RouteStep> steps) {
        this.steps = steps;
    }

    public void update(List<RouteStep> newSteps) {
        this.steps = newSteps;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_route_result, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RouteStep step = steps.get(position);

        holder.line.setText("Linia " + step.getLineNumber());

        StringBuilder sb = new StringBuilder();

        for (String stop : step.getStops()) {
            sb.append(stop).append("\n↓\n");
        }

        holder.stops.setText(sb.toString());

        holder.time.setText(step.getTravelTime() + " min");

        if (position != steps.size() - 1) {
            holder.transfer.setVisibility(View.VISIBLE);
        } else {
            holder.transfer.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView line, stops, time, transfer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            line = itemView.findViewById(R.id.lineNumber);
            stops = itemView.findViewById(R.id.routeStops);
            time = itemView.findViewById(R.id.travelTime);
            transfer = itemView.findViewById(R.id.transferLabel);
        }
    }
}