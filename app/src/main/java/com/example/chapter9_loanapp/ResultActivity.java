package com.example.chapter9_loanapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtResult;
    ImageView imgLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtResult = findViewById(R.id.txtResult);
        imgLoan = findViewById(R.id.imgLoan);

        // 1. نسترجع البيانات
        SharedPreferences prefs = getSharedPreferences("LoanData", MODE_PRIVATE);
        String loanValue = prefs.getString("loan", "0");
        String rateValue = prefs.getString("rate", "0");
        String yearsValue = prefs.getString("years", "3");

        double loan = Double.parseDouble(loanValue);
        double rate = Double.parseDouble(rateValue) / 100;
        int years = Integer.parseInt(yearsValue);

        // 2. حساب القسط الشهري
        double monthlyRate = rate / 12;
        int months = years * 12;
        double monthlyPayment =
                (loan * monthlyRate) /
                        (1 - Math.pow(1 + monthlyRate, -months));

        // 3. عرض الصورة حسب السنوات
        if (years == 3) {
            imgLoan.setImageResource(R.drawable.year3);
        } else if (years == 5) {
            imgLoan.setImageResource(R.drawable.year5);
        } else {
            imgLoan.setImageResource(R.drawable.year10);
        }

        // 4. عرض النتيجة
        txtResult.setText("Monthly Payment = " + String.format("%.2f", monthlyPayment));
    }
}