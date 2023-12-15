package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.UserInputLayoutBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;

public class UserInputActivity extends AppCompatActivity {
    private UserInputLayoutBinding binding;
    private int selectedHourPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserInputLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitEvent.setOnClickListener(view -> submitEvent());
        binding.financePage.setOnClickListener(this::switchPage);
        binding.timePage.setOnClickListener(this::switchPage);

    }

    private void saveEventToJson(String date, int hour, String eventName, String meaningfulTime) {
        try {
            JSONObject event = new JSONObject();
            event.put("date", date);
            event.put("hour", hour);
            event.put("eventName", eventName);
            event.put("meaningfulTime", meaningfulTime);

            File file = new File(getFilesDir(), "events.json");
            JSONArray eventsArray;
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                eventsArray = new JSONArray(content);
            } else {
                eventsArray = new JSONArray();
            }

            eventsArray.put(event);
            Files.write(file.toPath(), eventsArray.toString().getBytes());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }


    private void submitEvent() {
        String eventName = binding.eventName.getText().toString();
        String meaningfulTime = binding.meaningfulTime.getText().toString();
        String date = binding.inputDate.getText().toString();
        int hour = Integer.parseInt(binding.inputHour.getText().toString());

        if (eventName.isEmpty() || meaningfulTime.isEmpty() || date.isEmpty() || binding.inputHour.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a value for all of them", Toast.LENGTH_SHORT).show();
        } else {
            saveEventToJson(date, hour, eventName, meaningfulTime);

            Intent intent = new Intent(this, TimeActivity.class);
            startActivity(intent);
        }
    }

    public void switchPage(View view) {
        int id = view.getId();
        Intent intent;
        if (id == R.id.financePage) {
            intent = new Intent(this, FinanceActivity.class);
        }
        else {
            intent = new Intent(this, TimeActivity.class);
        }
        startActivity(intent);
    }
}
