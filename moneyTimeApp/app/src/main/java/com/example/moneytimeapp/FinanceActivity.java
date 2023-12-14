package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.FinanceActivityLayoutBinding;
import com.example.moneytimeapp.databinding.MainActivityLayoutBinding;
import com.example.moneytimeapp.databinding.TimeActivityLayoutBinding;
import com.google.android.material.navigation.NavigationView;

public class FinanceActivity extends AppCompatActivity {

    FinanceActivityLayoutBinding binding;
    private TextView textView;
    private Button button;

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

