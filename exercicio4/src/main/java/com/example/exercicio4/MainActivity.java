package com.example.exercicio4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private Button buttonVerify;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exercicio4);

        inputNumber = findViewById(R.id.input_number);
        buttonVerify = findViewById(R.id.button_verify);
        textResult = findViewById(R.id.text_result);

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(inputNumber.getText().toString());
                if (isPerfectNumber(number)) {
                    textResult.setText(number + " é um número perfeito!");
                } else {
                    textResult.setText(number + " não é um número perfeito!");
                }
            }
        });
    }

    private boolean isPerfectNumber(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }
}

