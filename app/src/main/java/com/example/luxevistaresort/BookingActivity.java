package com.example.luxevistaresort; // Make sure this package name is correct for your project

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //--- Initialize UI Elements ---
        ImageView backBtn = findViewById(R.id.backBtn);
        Button findRoomsBtn = findViewById(R.id.btnFindRooms);

        // Find the layouts that will trigger navigation
        // It's important to add IDs to these layouts in your XML file
        LinearLayout startDateLayout = findViewById(R.id.startDateLayout); // You will need to add this ID
        LinearLayout endDateLayout = findViewById(R.id.endDateLayout);     // You will need to add this ID
        LinearLayout roomsLayout = findViewById(R.id.roomsLayout);         // You will need to add this ID
        LinearLayout adultsLayout = findViewById(R.id.adultsLayout);       // You will need to add this ID
        LinearLayout childrenLayout = findViewById(R.id.childrenLayout);     // You will need to add this ID


        //--- Set up Click Listeners ---

        // Back button to finish the current activity and go to the previous screen
        backBtn.setOnClickListener(v -> finish());

        // A single listener for opening the date picker
        View.OnClickListener datePickerClickListener = v -> {
            // TODO: Replace DatePickerActivity.class with your actual date picker activity
            // For now, it shows a temporary message (Toast)
            // Intent intent = new Intent(BookingActivity.this, DatePickerActivity.class);
            // startActivity(intent);
            Toast.makeText(BookingActivity.this, "Opening Date Picker...", Toast.LENGTH_SHORT).show();
        };

        // Assign the date picker listener to both date layouts
        if (startDateLayout != null) {
            startDateLayout.setOnClickListener(datePickerClickListener);
        }
        if (endDateLayout != null) {
            endDateLayout.setOnClickListener(datePickerClickListener);
        }

        // A single listener for navigating to the "Find Rooms" screen
        View.OnClickListener findRoomsClickListener = v -> {
            // TODO: Replace FindRoomsActivity.class with your actual activity for find_rooms.xml
            // Intent intent = new Intent(BookingActivity.this, FindRoomsActivity.class);
            // startActivity(intent);
            Toast.makeText(BookingActivity.this, "Navigating to Find Rooms...", Toast.LENGTH_SHORT).show();
        };

        // Assign the "Find Rooms" listener to the occupancy sections and the main button
        if (roomsLayout != null) {
            roomsLayout.setOnClickListener(findRoomsClickListener);
        }
        if (adultsLayout != null) {
            adultsLayout.setOnClickListener(findRoomsClickListener);
        }
        if (childrenLayout != null) {
            childrenLayout.setOnClickListener(findRoomsClickListener);
        }
        findRoomsBtn.setOnClickListener(findRoomsClickListener);
    }
}
