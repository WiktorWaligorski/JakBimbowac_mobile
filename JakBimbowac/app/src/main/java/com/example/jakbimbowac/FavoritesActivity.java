package com.example.jakbimbowac;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        RecyclerView recyclerView =
                findViewById(R.id.favoritesRecyclerView);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        List<FavoriteItem> items = new ArrayList<>();

        Set<String> favLines =
                FavoritesManager.getFavoriteLines(this);

        Set<String> favStops =
                FavoritesManager.getFavoriteStops(this);

        if (!favLines.isEmpty()) {
            items.add(new FavoriteItem(
                    FavoriteItem.TYPE_HEADER,
                    "Lines",
                    null
            ));

            for (String line : favLines) {
                items.add(new FavoriteItem(
                        FavoriteItem.TYPE_LINE,
                        "Line " + line,
                        null
                ));
            }
        }

        if (!favStops.isEmpty()) {
            items.add(new FavoriteItem(
                    FavoriteItem.TYPE_HEADER,
                    "Stops",
                    null
            ));

            for (String stop : favStops) {
                items.add(new FavoriteItem(
                        FavoriteItem.TYPE_STOP,
                        stop,
                        null
                ));
            }
        }

        FavoritesAdapter adapter =
                new FavoritesAdapter(items);

        recyclerView.setAdapter(adapter);
    }
}