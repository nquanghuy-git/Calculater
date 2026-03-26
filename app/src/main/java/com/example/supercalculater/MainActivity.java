package com.example.supercalculater;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView expression;
    private TextView resultHolder;
    private String currentExpression = "";
    private String currentResult = "";

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

        expression = findViewById(R.id.expression);
        resultHolder = findViewById(R.id.resultHolder);

        // Number buttons
        setNumberButtonListener(R.id.btn0, "0");
        setNumberButtonListener(R.id.btn1, "1");
        setNumberButtonListener(R.id.btn2, "2");
        setNumberButtonListener(R.id.btn3, "3");
        setNumberButtonListener(R.id.btn4, "4");
        setNumberButtonListener(R.id.btn5, "5");
        setNumberButtonListener(R.id.btn6, "6");
        setNumberButtonListener(R.id.btn7, "7");
        setNumberButtonListener(R.id.btn8, "8");
        setNumberButtonListener(R.id.btn9, "9");

        // Operator buttons
        setOperatorButtonListener(R.id.btnAdd, "+");
        setOperatorButtonListener(R.id.btnSub, "-");
        setOperatorButtonListener(R.id.btnMul, "×");
        setOperatorButtonListener(R.id.btnDiv, "÷");

        // Dot button
        findViewById(R.id.btnDot).setOnClickListener(v -> {
            if (currentExpression.isEmpty()) {
                currentExpression = "0.";
            } else {
                String[] parts = currentExpression.split("[+\\-×÷]");
                String lastNumber = parts[parts.length - 1];
                if (!lastNumber.contains(".")) {
                    currentExpression += ".";
                }
            }
            updateDisplay();
        });

        // AC button - Clear all
        findViewById(R.id.btnAC).setOnClickListener(v -> {
            currentExpression = "";
            currentResult = "";
            updateDisplay();
        });

        // DEL button - Delete last character
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            if (!currentExpression.isEmpty()) {
                currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
                updateDisplay();
                calculateResult();
            }
        });

        // Percent button
        findViewById(R.id.btnPercent).setOnClickListener(v -> {
            if (!currentExpression.isEmpty()) {
                try {
                    double value = evaluateExpression(currentExpression);
                    value = value / 100;
                    currentExpression = formatResult(value);
                    currentResult = "";
                    updateDisplay();
                } catch (Exception e) {
                    // Ignore errors
                }
            }
        });

        // Negate button (+/-)
        findViewById(R.id.btnNegate).setOnClickListener(v -> {
            if (!currentExpression.isEmpty()) {
                try {
                    double value = evaluateExpression(currentExpression);
                    value = -value;
                    currentExpression = formatResult(value);
                    currentResult = "";
                    updateDisplay();
                } catch (Exception e) {
                    // Ignore errors
                }
            }
        });

        // Equals button
        findViewById(R.id.btnEq).setOnClickListener(v -> {
            if (!currentExpression.isEmpty()) {
                try {
                    double result = evaluateExpression(currentExpression);
                    currentExpression = formatResult(result);
                    currentResult = "";
                    updateDisplay();
                } catch (Exception e) {
                    resultHolder.setText("Error");
                }
            }
        });
    }

    private void setNumberButtonListener(int buttonId, String number) {
        findViewById(buttonId).setOnClickListener(v -> {
            currentExpression += number;
            updateDisplay();
            calculateResult();
        });
    }

    private void setOperatorButtonListener(int buttonId, String operator) {
        findViewById(buttonId).setOnClickListener(v -> {
            if (!currentExpression.isEmpty()) {
                char lastChar = currentExpression.charAt(currentExpression.length() - 1);
                if (lastChar == '+' || lastChar == '-' || lastChar == '×' || lastChar == '÷') {
                    currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
                }
                currentExpression += operator;
                updateDisplay();
            }
        });
    }

    private void updateDisplay() {
        expression.setText(currentExpression);
        if (!currentResult.isEmpty()) {
            resultHolder.setText(currentResult);
        } else {
            resultHolder.setText("");
        }
    }

    private void calculateResult() {
        try {
            if (!currentExpression.isEmpty() && !isLastCharOperator()) {
                double result = evaluateExpression(currentExpression);
                currentResult = formatResult(result);
                updateDisplay();
            }
        } catch (Exception e) {
            currentResult = "";
        }
    }

    private boolean isLastCharOperator() {
        if (currentExpression.isEmpty()) return false;
        char lastChar = currentExpression.charAt(currentExpression.length() - 1);
        return lastChar == '+' || lastChar == '-' || lastChar == '×' || lastChar == '÷';
    }

    private double evaluateExpression(String expr) {
        expr = expr.replace("×", "*").replace("÷", "/");
        return eval(expr);
    }

    private double eval(String expr) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expr.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expr.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            String formatted = String.format("%.10f", result);
            formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
            return formatted;
        }
    }
}
