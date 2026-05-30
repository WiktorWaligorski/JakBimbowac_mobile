package com.example.jakbimbowac;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {

    private static final String PREFS = "favorites";

    public static boolean isFavoriteLine(Context context, String lineNumber) {

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        return prefs.getStringSet("lines", new HashSet<>())
                .contains(lineNumber);
    }

    public static boolean isFavoriteStop(Context context, String stopName) {

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        return prefs.getStringSet("stops", new HashSet<>())
                .contains(stopName);
    }

    public static void toggleLine(Context context, String lineNumber) {

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        Set<String> lines =
                new HashSet<>(prefs.getStringSet("lines",
                        new HashSet<>()));

        if (lines.contains(lineNumber)) {
            lines.remove(lineNumber);
        } else {
            lines.add(lineNumber);
        }

        prefs.edit().putStringSet("lines", lines).apply();
    }

    public static void toggleStop(Context context, String stopName) {

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        Set<String> stops =
                new HashSet<>(prefs.getStringSet("stops",
                        new HashSet<>()));

        if (stops.contains(stopName)) {
            stops.remove(stopName);
        } else {
            stops.add(stopName);
        }

        prefs.edit().putStringSet("stops", stops).apply();
    }

    public static Set<String> getFavoriteLines(Context context) {

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        return new HashSet<>(
                prefs.getStringSet("lines", new HashSet<>()));
    }

    public static Set<String> getFavoriteStops(Context context) {

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        return new HashSet<>(
                prefs.getStringSet("stops", new HashSet<>()));
    }
}