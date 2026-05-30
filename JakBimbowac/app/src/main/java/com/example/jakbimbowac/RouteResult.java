package com.example.jakbimbowac;

import java.util.List;

public class RouteResult {

    private final List<RouteStep> steps;

    public RouteResult(List<RouteStep> steps) {
        this.steps = steps;
    }

    public List<RouteStep> getSteps() {
        return steps;
    }

    public int getTransfers() {

        if (steps.isEmpty()) {
            return 0;
        }

        return steps.size() - 1;
    }

    public int getTotalTime() {

        int total = 0;

        for (RouteStep step : steps) {
            total += step.getTravelTime();
        }

        return total;
    }
}