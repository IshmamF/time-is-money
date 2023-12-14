package com.example.moneytimeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;
import com.example.moneytimeapp.model.HourInfo;

public class HourAdapter extends ArrayAdapter<HourInfo> {

    // Corrected constructor
    public HourAdapter(Context context, List<HourInfo> hourInfos) {
        super(context, 0, hourInfos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        HourInfo hourInfo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour_activity_layout, parent, false);
        }

        TextView hourTextView = convertView.findViewById(R.id.hour);
        TextView eventDescription = convertView.findViewById(R.id.eventDescription);
        TextView meaningfulTime = convertView.findViewById(R.id.meaningfulTime);

        // Assuming hourInfo is not null and has the required getters
        hourTextView.setText(hourInfo.getTime().toString());
        eventDescription.setText(hourInfo.getEvent());
        meaningfulTime.setText(String.valueOf(hourInfo.getMeaninngful()));

        return convertView;
    }
}
