package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.HourActivityLayoutBinding;
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
    HourActivityLayoutBinding hourBinding;

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

        setupHourListView();
        DateAndDay();

    }
    // Inside TimeActivity.java
    private void setupHourListView() {
        List<HourInfo> hourInfos = createHourInfoList();
        HourAdapter adapter = new HourAdapter(this, hourInfos);
        binding.hourView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String eventName = data.getStringExtra("EVENT_NAME");
            int meaningfulTime = data.getIntExtra("MEANINGFUL_TIME", 0);
            int hourPosition = data.getIntExtra("HOUR_POSITION", -1);

            if (hourPosition != -1) {
                HourInfo updatedHourInfo = new HourInfo(LocalTime.of(hourPosition, 0), eventName, meaningfulTime);
                updateHourInfo(hourPosition, updatedHourInfo);
            }
        }
    }

    private void updateHourInfo(int position, HourInfo updatedHourInfo) {
        List<HourInfo> hourInfos = ((HourAdapter) binding.hourView.getAdapter()).getItems();
        hourInfos.set(position, updatedHourInfo);
        ((ArrayAdapter) binding.hourView.getAdapter()).notifyDataSetChanged();
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
