package com.example.moneytimeapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.FinanceActivityLayoutBinding;
import com.example.moneytimeapp.databinding.HourActivityLayoutBinding;

public class HourActivity extends AppCompatActivity {

    HourActivityLayoutBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HourActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
