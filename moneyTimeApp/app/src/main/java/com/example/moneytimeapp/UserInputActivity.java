package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.UserInputLayoutBinding;

// Inside UserInputActivity.java
public class UserInputActivity extends AppCompatActivity {
    private UserInputLayoutBinding binding;
    private int selectedHourPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserInputLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        selectedHourPosition = getIntent().getIntExtra("SELECTED_HOUR", -1); // Default to -1 if not found

        binding.submitEvent.setOnClickListener(view -> submitEvent());
    }

    private void submitEvent() {
        String eventName = binding.eventName.getText().toString();
        String meaningfulTime = binding.meaningfulTime.getText().toString();

        if (!eventName.isEmpty() && !meaningfulTime.isEmpty()) {
            Intent data = new Intent();
            data.putExtra("EVENT_NAME", eventName);
            data.putExtra("MEANINGFUL_TIME", Integer.parseInt(meaningfulTime));
            data.putExtra("HOUR_POSITION", selectedHourPosition);
            setResult(RESULT_OK, data);
            Intent intent = new Intent(this, TimeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

}
