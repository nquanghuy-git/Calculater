package com.example.supercalculater;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final TextView expression = findViewById(R.id.expression);
        final TextView resultHolder = findViewById(R.id.resultHolder);
        
        // Set initial text to empty string to ensure TextView is ready
        expression.setText("");

        final AppCompatButton  btnBack = findViewById(R.id.btnBack);
        final AppCompatButton  btnAC = findViewById(R.id.btnAC);
        final AppCompatButton  btnPercent = findViewById(R.id.btnPercent);
        final AppCompatButton  btnDiv = findViewById(R.id.btnDiv);
        final AppCompatButton  btn7 = findViewById(R.id.btn7);
        final AppCompatButton  btn8 = findViewById(R.id.btn8);
        final AppCompatButton  btn9 = findViewById(R.id.btn9);
        final AppCompatButton  btnMul = findViewById(R.id.btnMul);
        final AppCompatButton  btn4 = findViewById(R.id.btn4);
        final AppCompatButton  btn5 = findViewById(R.id.btn5);
        final AppCompatButton  btn6 = findViewById(R.id.btn6);
        final AppCompatButton  btnSub = findViewById(R.id.btnSub);
        final AppCompatButton  btn1 = findViewById(R.id.btn1);
        final AppCompatButton  btn2 = findViewById(R.id.btn2);
        final AppCompatButton  btn3 = findViewById(R.id.btn3);
        final AppCompatButton  btnAdd = findViewById(R.id.btnAdd);
        final AppCompatButton  btnNegate = findViewById(R.id.btnNegate);
        final AppCompatButton  btn0 = findViewById(R.id.btn0);
        final AppCompatButton  btnDot = findViewById(R.id.btnDot);
        final AppCompatButton  btnEq = findViewById(R.id.btnEq);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.setText("");
                resultHolder.setText("");
            }
        });

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expressionText = expression.getText().toString();
                String newText = expressionText + "7";
                expression.setText(newText);
                Log.d("Calculator", "Button 7 clicked. Old: '" + expressionText + "' New: '" + newText + "'");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                expression.setText(expressionText + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                expression.setText(expressionText + "9");
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                expression.setText(expressionText + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                expression.setText(expressionText + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                expression.setText(expressionText + "6");
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                expression.setText(expressionText + "1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                expression.setText(expressionText + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                expression.setText(expressionText + "3");
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnNegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expressionText = expression.getText().toString();
                expression.setText(expressionText + "0");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }
}