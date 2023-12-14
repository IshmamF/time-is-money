package com.example.moneytimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneytimeapp.databinding.FinanceActivityLayoutBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class FinanceActivity extends AppCompatActivity {

    FinanceActivityLayoutBinding binding;

    private static int totalCost = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FinanceActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.timePage.setOnClickListener(this::switchPage);
        binding.submitFinance.setOnClickListener(this::submitInfo);

        updateTotalCostDisplay();
    }

    private void submitInfo(View view) {
        String costString = binding.expenseEditText.getText().toString();
        String expense = binding.lastExpenseInput.getEditableText().toString();
        if (!costString.isEmpty()) {
            try {
                int cost = Integer.parseInt(costString);
                totalCost += cost; // Update the static variable
                updateTotalCostDisplay(); // Update the TextView
                binding.lastExpense.setText(expense);

                // Clear the input fields after submitting
                binding.expenseEditText.setText("");
                binding.lastExpenseInput.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid number for cost.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a cost.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTotalCostDisplay() {
        binding.costNum.setText("Total Cost: " + totalCost);
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

