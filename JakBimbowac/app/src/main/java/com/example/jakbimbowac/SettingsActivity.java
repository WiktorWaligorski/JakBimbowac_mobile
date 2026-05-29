package com.example.jakbimbowac;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup themeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        themeGroup = findViewById(R.id.themeGroup);

        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        int selectedTheme = preferences.getInt("theme", 0);

        switch (selectedTheme) {
            case 1:
                ((RadioButton) findViewById(R.id.radioLight)).setChecked(true);
                break;
            case 2:
                ((RadioButton) findViewById(R.id.radioDark)).setChecked(true);
                break;
            default:
                ((RadioButton) findViewById(R.id.radioSystem)).setChecked(true);
                break;
        }

        themeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            SharedPreferences.Editor editor = preferences.edit();

            if (checkedId == R.id.radioLight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putInt("theme", 1);
            }
            else if (checkedId == R.id.radioDark) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putInt("theme", 2);
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                editor.putInt("theme", 0);
            }

            editor.apply();
        });
    }
}