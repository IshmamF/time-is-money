package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.FinanceActivityLayoutBinding;

public class FinanceActivity extends AppCompatActivity {
    FinanceActivityLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FinanceActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.timePage.setOnClickListener(this::switchPage);
        binding.homePage.setOnClickListener(this::switchPage);
    }

    public void switchPage(View view) {
        int id = view.getId();
        Intent intent;
        if (id == R.id.homePage) {
            intent = new Intent(this, MainActivity.class);
        }
        else {
            intent = new Intent(this, TimeActivity.class);
        }
        startActivity(intent);
    }
}
