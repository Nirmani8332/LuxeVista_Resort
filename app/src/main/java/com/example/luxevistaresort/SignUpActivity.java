package com.example.luxevistaresort;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etPassword, etRePassword;
    Button btnRegister;
    ImageView backBtn;
    TextView tvLogin;
    // 🛑 New: Database Helper instance
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 🛑 Initialize Database Helper
        dbHelper = new DBHelper(this);

        // --- Find Views ---
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        btnRegister = findViewById(R.id.btnRegister);
        backBtn = findViewById(R.id.backBtn);
        tvLogin = findViewById(R.id.tvLogin);

        // --- Action: Registration Attempt (MODIFIED Logic) ---
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String rePassword = etRePassword.getText().toString().trim();

                if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(rePassword)) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 🛑 Check if the email already exists 🛑
                if (dbHelper.checkEmailExists(email)) {
                    Toast.makeText(SignUpActivity.this, "This email is already registered.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 🛑 Insert user into the database 🛑
                boolean isInserted = dbHelper.insertUser(email, fullName, password);

                if (isInserted) {
                    Toast.makeText(SignUpActivity.this, "Registration Successful! Please log in.", Toast.LENGTH_LONG).show();

                    // Navigate to LoginActivity
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // --- Navigation: Back Button (unchanged) ---
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // --- Navigation: Go to LoginActivity (unchanged) ---
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
