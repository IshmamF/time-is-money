package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.TimeActivityLayoutBinding;
import com.example.moneytimeapp.model.HourInfo;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TimeActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.forward.setOnClickListener(this::updateDateAndDay);
        binding.backword.setOnClickListener(this::updateDateAndDay);
        binding.homePage.setOnClickListener(this::switchPage);
        binding.financePage.setOnClickListener(this::switchPage);
        binding.addEvent.setOnClickListener(this::eventPage);

        binding.hourView.setOnItemClickListener((parent, view, position, id) -> {
            HourInfo selectedHour = (HourInfo) parent.getItemAtPosition(position);
            // Start HourActivity with the selected hour's details
            Intent intent = new Intent(TimeActivity.this, HourActivity.class);
            // Optionally, pass the selected hour info to HourActivity
            startActivity(intent);
        });


        setupHourListView();
        DateAndDay();

    }

    private void eventPage(View view) {
        Intent intent = new Intent(this, HourActivity.class);
        startActivity(intent);

    }

    private void setupHourListView() {
        List<HourInfo> hourInfos = createHourInfoList(); // Create a list of HourInfo
        HourAdapter adapter = new HourAdapter(this, hourInfos);
        binding.hourView.setAdapter(adapter);
    }

    private List<HourInfo> createHourInfoList() {
        List<HourInfo> list = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            // Replace with actual event and meaningful time data if available
            list.add(new HourInfo(LocalTime.of(hour, 0), "No Event", 0));
        }
        return list;
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
