package com.example.moneytimeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.TimeActivityLayoutBinding;
import com.example.moneytimeapp.model.HourInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TimeActivity extends AppCompatActivity {
    TimeActivityLayoutBinding binding;
    private Calendar c = Calendar.getInstance();

    private int totalMeaningfulTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TimeActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.forward.setOnClickListener(this::updateDateAndDay);
        binding.backword.setOnClickListener(this::updateDateAndDay);
        binding.homePage.setOnClickListener(this::switchPage);
        binding.financePage.setOnClickListener(this::switchPage);

        binding.addEvent.setOnClickListener(this::inputPage);
        DateAndDay();

    }

    private void inputPage(View view) {
        Intent intent = new Intent(this, UserInputActivity.class);
        startActivity(intent);
    }

    private void loadEventsForDate(String date) {
        clearEventRows();
        totalMeaningfulTime = 0;

        try {
            File file = new File(getFilesDir(), "events.json");
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                JSONArray eventsArray = new JSONArray(content);

                for (int i = 0; i < eventsArray.length(); i++) {
                    JSONObject event = eventsArray.getJSONObject(i);
                    if (event.getString("date").equals(date)) {
                        int hour = event.getInt("hour");
                        String eventName = event.getString("eventName");
                        int meaningfulTime = event.optInt("meaningfulTime", 0);

                        updateEventRow(hour, eventName, meaningfulTime);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView totalMeaningfulTimeTextView = findViewById(R.id.totalMeaningfulTime);
        if (totalMeaningfulTimeTextView != null) {
            totalMeaningfulTimeTextView.setText("Total Meaningful Time Today: " + String.valueOf(totalMeaningfulTime));
        }
    }

    private void updateEventRow(int hour, String eventName, int meaningfulTime) {
        String hourString = String.format(Locale.getDefault(), "%02d", hour);
        int eventTextViewId = getResources().getIdentifier("event" + hourString, "id", getPackageName());
        int timeTextViewId = getResources().getIdentifier("number" + hourString, "id", getPackageName());

        TextView eventTextView = findViewById(eventTextViewId);
        TextView timeTextView = findViewById(timeTextViewId);

        if (eventTextView != null && timeTextView != null) {
            eventTextView.setText(eventName);
            timeTextView.setText(String.valueOf(meaningfulTime));
        }
        totalMeaningfulTime += meaningfulTime;
    }




    private void updateDateAndDay(View view) {
        int id = view.getId();
        int increment;
        if (id == R.id.forward) {
            increment = 1;
        } else {
            increment = -1;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

        c.add(Calendar.DAY_OF_YEAR, increment);

        String formattedDate = dateFormat.format(c.getTime());
        String dayOfWeek = dayOfWeekFormat.format(c.getTime());

        binding.date.setText(formattedDate);
        binding.dayOfWeek.setText(dayOfWeek);

        loadEventsForDate(formattedDate);
    }

    private void clearEventRows() {
        for (int i = 0; i <= 24; i++) {
            int eventTextViewId = getResources().getIdentifier("event" + i, "id", getPackageName());
            int timeTextViewId = getResources().getIdentifier("number" + i, "id", getPackageName());

            TextView eventTextView = findViewById(eventTextViewId);
            TextView timeTextView = findViewById(timeTextViewId);

            if (eventTextView != null && timeTextView != null) {
                eventTextView.setText("");
                timeTextView.setText("");
            }
        }
    }


    private void DateAndDay() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

        String date = dateFormat.format(c.getTime());
        String dayOfWeek = dayOfWeekFormat.format(c.getTime());

        binding.date.setText(date);
        binding.dayOfWeek.setText(dayOfWeek);
    }


    public void switchPage(View view) {
        int id = view.getId();
        Intent intent;
        if (id == R.id.homePage) {
            intent = new Intent(this, MainActivity.class);
        }
        else {
            intent = new Intent(this, FinanceActivity.class);
        }
        startActivity(intent);
    }

}
