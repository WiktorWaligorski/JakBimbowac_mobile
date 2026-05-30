package com.example.jakbimbowac;

import java.util.List;

public class RouteStep {

    private final String lineNumber;
    private final List<String> stops;

    public RouteStep(String lineNumber, List<String> stops) {
        this.lineNumber = lineNumber;
        this.stops = stops;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public List<String> getStops() {
        return stops;
    }

    public int getTravelTime() {
        return (stops.size() - 1) * 10;
    }
}