package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.TimeActivityLayoutBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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

        DateAndDay();
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
