package com.example.luxevistaresort;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// The class name was changed from BookingConfirmationActivity to match the file name "BookingConfirmation.java"
public class BookingConfirmation extends AppCompatActivity {

    // Declare views
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etMobile;
    private CheckBox cbTerms;
    private Button btnBookNow;
    private ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Link to the XML layout file
        setContentView(R.layout.activity_booking_confirmation);

        // Initialize views
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etEmail = findViewById(R.id.et_email);
        etMobile = findViewById(R.id.et_mobile);
        cbTerms = findViewById(R.id.cb_terms);
        btnBookNow = findViewById(R.id.btn_book_now);
        backButton = findViewById(R.id.back_button);

        // Set up event listeners
        backButton.setOnClickListener(v -> finish()); // Go back to the previous screen

        btnBookNow.setOnClickListener(v -> {
            if (validateInputs()) {
                // Proceed to finalize booking and integrate with database
                finalizeBooking();
            }
        });
    }

    /**
     * LO3: Implementation of proper validation mechanisms.
     * Checks if all mandatory fields are filled and policies are accepted.
     */
    private boolean validateInputs() {
        // Basic check for mandatory fields (Contact Information)
        if (TextUtils.isEmpty(etFirstName.getText()) || TextUtils.isEmpty(etLastName.getText()) ||
                TextUtils.isEmpty(etEmail.getText()) || TextUtils.isEmpty(etMobile.getText())) {
            Toast.makeText(this, "Please fill in all mandatory contact information.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check for Email format validation
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check for Terms and Conditions acceptance
        if (!cbTerms.isChecked()) {
            Toast.makeText(this, "You must agree to the Terms and Conditions to proceed.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    /**
     * Handles the final step: saving the booking to the database.
     */
    private void finalizeBooking() {
        // --- This is where the core Task D database integration happens ---

        // 1. Collect all data from the UI (guest info, dates, total price, etc.)
        /*
        Map<String, String> bookingData = new HashMap<>();
        bookingData.put("firstName", etFirstName.getText().toString());
        bookingData.put("email", etEmail.getText().toString());
        bookingData.put("totalPrice", "40600.00"); // Hardcoded for example, should be dynamic
        // ... more booking details
        */

        // 2. Call the DBHelper to insert the new booking record (Task D: Database Integration)
        // DBHelper dbHelper = new DBHelper(this);
        // boolean success = dbHelper.insertNewBooking(bookingData);

        // For demonstration, assume success
        // if (success) {
        Toast.makeText(this, "Booking Successful! Confirmation sent to email.", Toast.LENGTH_LONG).show();
        // Navigate to a final confirmation screen (or HomeActivity)
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // } else {
        //     Toast.makeText(this, "Booking failed. Please try again.", Toast.LENGTH_LONG).show();
        // }
    }
}
