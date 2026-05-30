package com.example.jakbimbowac;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class LinesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);

        recyclerView = findViewById(R.id.linesRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Line> lines = loadLinesFromJson();

        recyclerView.setAdapter(new LinesAdapter(lines));
    }

    private List<Line> loadLinesFromJson() {

        String json = loadJSONFromAsset("lines.json");

        Gson gson = new Gson();

        Type listType = new TypeToken<List<Line>>() {}.getType();

        return gson.fromJson(json, listType);
    }

    private String loadJSONFromAsset(String fileName) {

        StringBuilder builder = new StringBuilder();

        try {
            InputStream inputStream = getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}