package com.example.jakbimbowac;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class StopsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);

        recyclerView = findViewById(R.id.stopsRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Stop> stops = loadStops();

        List<Line> lines = loadLines();

        recyclerView.setAdapter(new StopsAdapter(stops, lines));
    }

    private List<Stop> loadStops() {

        String json = loadJSON("stops.json");

        Type type = new TypeToken<List<Stop>>() {}.getType();

        return new Gson().fromJson(json, type);
    }

    private List<Line> loadLines() {

        String json = loadJSON("lines.json");

        Type type = new TypeToken<List<Line>>() {}.getType();

        return new Gson().fromJson(json, type);
    }

    private String loadJSON(String fileName) {

        StringBuilder builder = new StringBuilder();

        try {

            InputStream is = getAssets().open(fileName);

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(is));

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}