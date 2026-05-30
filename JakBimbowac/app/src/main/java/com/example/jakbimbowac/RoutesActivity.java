package com.example.jakbimbowac;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoutesActivity extends AppCompatActivity {

    private List<Line> lines = new ArrayList<>();
    private List<Stop> stops = new ArrayList<>();

    private AutoCompleteTextView startInput, endInput;
    private RouteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        startInput = findViewById(R.id.startStopInput);
        endInput = findViewById(R.id.endStopInput);
        Button search = findViewById(R.id.searchRouteButton);

        RecyclerView rv = findViewById(R.id.routesRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RouteAdapter(new ArrayList<>());
        rv.setAdapter(adapter);

        loadData();

        List<String> stopNames = new ArrayList<>();
        for (Stop s : stops) stopNames.add(s.getName());

        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_dropdown_item_1line,
                        stopNames);

        startInput.setAdapter(spinnerAdapter);
        endInput.setAdapter(spinnerAdapter);

        search.setOnClickListener(v -> {

            String start = startInput.getText().toString();
            String end = endInput.getText().toString();

            RouteResult result =
                    RouteFinder.findRoute(start, end, lines);

            adapter.update(result.getSteps());
        });
    }

    private void loadData() {

        Gson gson = new Gson();

        lines = gson.fromJson(
                readFile("lines.json"),
                new TypeToken<List<Line>>() {}.getType()
        );

        stops = gson.fromJson(
                readFile("stops.json"),
                new TypeToken<List<Stop>>() {}.getType()
        );
    }

    private String readFile(String fileName) {

        try {
            InputStream is = getAssets().open(fileName);
            Scanner scanner = new Scanner(is);
            StringBuilder sb = new StringBuilder();

            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

            return sb.toString();

        } catch (Exception e) {
            return "";
        }
    }
}