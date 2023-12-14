package com.example.moneytimeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import com.example.moneytimeapp.model.HourInfo;

public class HourAdapter extends ArrayAdapter<HourInfo> {
    private final ArrayList<HourInfo> hourInfos;

    public HourAdapter(Context context, List<HourInfo> hourInfos) {
        super(context, 0, hourInfos);
        this.hourInfos = new ArrayList<>(hourInfos); // Copy the list
    }

    public ArrayList<HourInfo> getItems() {
        return hourInfos; // Assuming you have a private field List<HourInfo> hourInfos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        HourInfo hourInfo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour_activity_layout, parent, false);
        }

        TextView hourTextView = convertView.findViewById(R.id.hour);
        Button eventButton = convertView.findViewById(R.id.eventDescription);
        TextView meaningfulTime = convertView.findViewById(R.id.meaningfulTime);

        hourTextView.setText(hourInfo.getTime().toString());
        eventButton.setText(hourInfo.getEvent());
        meaningfulTime.setText(String.valueOf(hourInfo.getMeaninngful()));

        eventButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UserInputActivity.class);
            intent.putExtra("SELECTED_HOUR", position);
            ((Activity) getContext()).startActivityForResult(intent, 1); // Use a constant for the request code
        });

        return convertView;
    }
}

