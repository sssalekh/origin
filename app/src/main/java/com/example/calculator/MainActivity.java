package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private double value1 = Double.NaN; // Stores first operand
    private double value2 = Double.NaN; // Stores second operand
    private char currentAction; // Stores current operation
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char SQUARE_ROOT = '√';
    private final char EQUALS = '=';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.editTextDisplay);
        setupButtons();
    }

    private void setupButtons() {
        // Numbers and Decimal Point
        findViewById(R.id.btn0).setOnClickListener(v -> display.append("0"));
        findViewById(R.id.btn1).setOnClickListener(v -> display.append("1"));
        findViewById(R.id.btn2).setOnClickListener(v -> display.append("2"));
        findViewById(R.id.btn3).setOnClickListener(v -> display.append("3"));
        findViewById(R.id.btn4).setOnClickListener(v -> display.append("4"));
        findViewById(R.id.btn5).setOnClickListener(v -> display.append("5"));
        findViewById(R.id.btn6).setOnClickListener(v -> display.append("6"));
        findViewById(R.id.btn7).setOnClickListener(v -> display.append("7"));
        findViewById(R.id.btn8).setOnClickListener(v -> display.append("8"));
        findViewById(R.id.btn9).setOnClickListener(v -> display.append("9"));
        findViewById(R.id.btnDecimal).setOnClickListener(v -> display.append("."));

        // Arithmetic Operations
        findViewById(R.id.btnAdd).setOnClickListener(v -> handleOperation(ADDITION));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> handleOperation(SUBTRACTION));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> handleOperation(MULTIPLICATION));
        findViewById(R.id.btnDivide).setOnClickListener(v -> handleOperation(DIVISION));
        findViewById(R.id.btnSquareRoot).setOnClickListener(v -> handleSquareRoot());

        // Clear Button
        findViewById(R.id.btnC).setOnClickListener(v -> clear());

        // Equals Button
        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            if (currentAction != EQUALS) {
                value2 = Double.parseDouble(display.getText().toString());
                performOperation();
                display.setText(String.valueOf(value1));
                value1 = Double.NaN;
            }
        });


        findViewById(R.id.btnSignChange).setOnClickListener(v -> toggleSign());


        findViewById(R.id.btnBackspace).setOnClickListener(v -> backspace());
    }

    private void handleOperation(char action) {

        if (display.getText().length() != 0) {
            if (!Double.isNaN(value1)) {
                value2 = Double.parseDouble(display.getText().toString());
                performOperation();
            } else {
                value1 = Double.parseDouble(display.getText().toString());
            }
            currentAction = action;
            display.setText("");
        }
    }

    private void performOperation() {
        switch (currentAction) {
            case ADDITION:
                value1 = value1 + value2;
                break;
            case SUBTRACTION:
                value1 = value1 - value2;
                break;
            case MULTIPLICATION:
                value1 = value1 * value2;
                break;
            case DIVISION:
                if (value2 != 0) {
                    value1 = value1 / value2;
                } else {
                    display.setText("Error");
                    clear();
                    return;
                }
                break;
        }
        value2 = Double.NaN;
    }

    private void handleSquareRoot() {
        if (display.getText().length() != 0) {
            double value = Double.parseDouble(display.getText().toString());
            display.setText(String.valueOf(Math.sqrt(value)));
        }
    }

    private void clear() {
        display.setText("");
        value1 = Double.NaN;
        value2 = Double.NaN;
    }

    private void toggleSign() {
        if (display.getText().length() != 0) {
            double value = Double.parseDouble(display.getText().toString());
            value = value * -1;
            display.setText(String.valueOf(value));
        }
    }

    private void backspace() {
        String text = display.getText().toString();
        if (!text.isEmpty()) {
            display.setText(text.substring(0, text.length() - 1));
        }
    }
}
