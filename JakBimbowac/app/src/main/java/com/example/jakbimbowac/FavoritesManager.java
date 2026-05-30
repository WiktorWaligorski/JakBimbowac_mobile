package com.example.jakbimbowac;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {

    private static final String PREFS = "favorites_prefs";

    private static final String KEY_STOPS = "favorite_stops";
    private static final String KEY_LINES = "favorite_lines";

    // =========================
    // STOPS
    // =========================

    public static boolean isFavoriteStop(Context context, String name) {
        return getStops(context).contains(name);
    }

    public static void addStop(Context context, String name) {
        Set<String> set = getStops(context);
        set.add(name);
        saveStops(context, set);
    }

    public static void removeStop(Context context, String name) {
        Set<String> set = getStops(context);
        set.remove(name);
        saveStops(context, set);
    }

    public static void toggleStop(Context context, String name) {
        if (isFavoriteStop(context, name)) {
            removeStop(context, name);
        } else {
            addStop(context, name);
        }
    }

    public static Set<String> getFavoriteStops(Context context) {
        return getStops(context);
    }

    // =========================
    // LINES
    // =========================

    public static boolean isFavoriteLine(Context context, String lineNumber) {
        return getLines(context).contains(lineNumber);
    }

    public static void addLine(Context context, String lineNumber) {
        Set<String> set = getLines(context);
        set.add(lineNumber);
        saveLines(context, set);
    }

    public static void removeLine(Context context, String lineNumber) {
        Set<String> set = getLines(context);
        set.remove(lineNumber);
        saveLines(context, set);
    }

    public static void toggleLine(Context context, String lineNumber) {
        if (isFavoriteLine(context, lineNumber)) {
            removeLine(context, lineNumber);
        } else {
            addLine(context, lineNumber);
        }
    }

    public static Set<String> getFavoriteLines(Context context) {
        return getLines(context);
    }

    // =========================
    // INTERNAL
    // =========================

    private static Set<String> getStops(Context context) {
        return new HashSet<>(
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                        .getStringSet(KEY_STOPS, new HashSet<>())
        );
    }

    private static Set<String> getLines(Context context) {
        return new HashSet<>(
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                        .getStringSet(KEY_LINES, new HashSet<>())
        );
    }

    private static void saveStops(Context context, Set<String> set) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putStringSet(KEY_STOPS, set)
                .apply();
    }

    private static void saveLines(Context context, Set<String> set) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putStringSet(KEY_LINES, set)
                .apply();
    }
}