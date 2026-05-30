package com.example.jakbimbowac;

public class FavoriteItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_LINE = 1;
    public static final int TYPE_STOP = 2;

    private final int type;
    private final String title;
    private final String subtitle;

    public FavoriteItem(
            int type,
            String title,
            String subtitle
    ) {
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}