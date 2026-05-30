package com.example.jakbimbowac;

public class Edge {

    private final String from;
    private final String to;
    private final String line;

    public Edge(
            String from,
            String to,
            String line
    ) {
        this.from = from;
        this.to = to;
        this.line = line;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getLine() {
        return line;
    }
}