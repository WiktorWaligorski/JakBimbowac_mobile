package com.example.jakbimbowac;

import java.util.*;

public class RouteFinder {

    private static class State {
        String stop;
        String line;
        List<RouteStep> steps;
        Set<String> visitedStops;

        State(String stop, String line, List<RouteStep> steps, Set<String> visitedStops) {
            this.stop = stop;
            this.line = line;
            this.steps = steps;
            this.visitedStops = visitedStops;
        }
    }

    public static RouteResult findRoute(
            String start,
            String end,
            List<Line> lines
    ) {

        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new State(start, null, new ArrayList<>(), new HashSet<>()));

        while (!queue.isEmpty()) {

            State current = queue.poll();

            if (current.stop.equals(end)) {
                return new RouteResult(current.steps);
            }

            if (visited.contains(current.stop + "|" + current.line)) {
                continue;
            }

            visited.add(current.stop + "|" + current.line);

            for (Line line : lines) {

                List<String> route = line.getRoute_forward();

                exploreLine(queue, current, line, route, false, end);

                route = line.getRoute_backward();

                exploreLine(queue, current, line, route, true, end);
            }
        }

        return new RouteResult(new ArrayList<>());
    }

    private static void exploreLine(
            Queue<State> queue,
            State current,
            Line line,
            List<String> route,
            boolean backward,
            String end
    ) {

        int index = route.indexOf(current.stop);

        if (index == -1) return;

        for (int i = index + 1; i < route.size(); i++) {

            String nextStop = route.get(i);

            List<RouteStep> newSteps = new ArrayList<>(current.steps);

            if (current.line == null || !current.line.equals(line.getNumber())) {

                List<String> stepStops = route.subList(index, i + 1);

                newSteps.add(new RouteStep(
                        line.getNumber(),
                        new ArrayList<>(stepStops)
                ));
            } else {

                RouteStep last = newSteps.get(newSteps.size() - 1);

                List<String> extended = new ArrayList<>(last.getStops());
                extended.add(nextStop);

                newSteps.set(newSteps.size() - 1,
                        new RouteStep(line.getNumber(), extended));
            }

            Set<String> newVisited = new HashSet<>(current.visitedStops);
            newVisited.add(nextStop);

            queue.add(new State(
                    nextStop,
                    line.getNumber(),
                    newSteps,
                    newVisited
            ));

            if (nextStop.equals(end)) {
                return;
            }
        }
    }
}