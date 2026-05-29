package com.example.jakbimbowac;

import java.util.List;
import java.util.Map;

public class Line {

    private String number;
    private String color;
    private List<String> route_forward;
    private List<String> route_backward;
    private Map<String, List<String>> schedule;

    public String getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public List<String> getRoute_forward() {
        return route_forward;
    }

    public List<String> getRoute_backward() {
        return route_backward;
    }

    public Map<String, List<String>> getSchedule() {
        return schedule;
    }
}