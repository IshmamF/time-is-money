package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.MainActivityLayoutBinding;

public class MainActivity extends AppCompatActivity {
    MainActivityLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.timePage.setOnClickListener(this::switchPage);
        binding.financePage.setOnClickListener(this::switchPage);

    }

    public void switchPage(View view) {
        int id = view.getId();
        Intent intent;
        if (id == R.id.timePage) {
            intent = new Intent(this, TimeActivity.class);
        }
        else {
            intent = new Intent(this, FinanceActivity.class);
        }
        startActivity(intent);
    }
}
