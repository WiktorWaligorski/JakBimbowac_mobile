package com.example.jakbimbowac;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        TextView favoritesLines =
                findViewById(R.id.favoriteLines);

        TextView favoritesStops =
                findViewById(R.id.favoriteStops);

        Set<String> lines =
                FavoritesManager.getFavoriteLines(this);

        Set<String> stops =
                FavoritesManager.getFavoriteStops(this);

        StringBuilder linesText =
                new StringBuilder();

        for (String line : lines) {
            linesText.append(line).append("\n");
        }

        StringBuilder stopsText =
                new StringBuilder();

        for (String stop : stops) {
            stopsText.append(stop).append("\n");
        }

        favoritesLines.setText(linesText.toString());
        favoritesStops.setText(stopsText.toString());
    }
}