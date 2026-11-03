package com.example.chapter9_loanapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText txtLoan, txtRate;
    Spinner spYears;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLoan = findViewById(R.id.txtLoan);
        txtRate = findViewById(R.id.txtRate);
        spYears = findViewById(R.id.spYears);
        btnCalculate = findViewById(R.id.btnCalculate);

        String[] years = {"3", "5", "10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                years
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spYears.setAdapter(adapter);

        btnCalculate.setOnClickListener(v -> {
            // 1. نحفظ البيانات
            SharedPreferences prefs = getSharedPreferences("LoanData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("loan", txtLoan.getText().toString());
            editor.putString("rate", txtRate.getText().toString());
            editor.putString("years", spYears.getSelectedItem().toString());
            editor.apply(); // حفظ دائم

            // 2. نفتح الصفحة الثانية
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(intent);
        });
    }
}