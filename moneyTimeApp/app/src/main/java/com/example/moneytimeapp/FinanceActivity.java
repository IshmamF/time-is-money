package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.MainActivityLayoutBinding;
import com.google.android.material.navigation.NavigationView;

public class FinanceActivity extends AppCompatActivity implements View.OnClickListener {

    MainActivityLayoutBinding binding;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout_for_insertdata);

        textView = findViewById(R.id.amt_val);
        button = findViewById(R.id.btnCancel);

        button.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnConfirm) {
            textView.setText("Your information is saved.");
        } else if (v.getId() == R.id.btnCancel) {
            textView.setText("The draft is canceled");
        }
    }

}

