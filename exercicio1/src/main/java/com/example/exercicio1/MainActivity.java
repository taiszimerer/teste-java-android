package com.example.exercicio1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> numbers = new ArrayList<Integer>();
    private EditText editTextNumber;
    private TextView textViewNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        textViewNumbers = (TextView) findViewById(R.id.textViewNumbers);

        findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editTextNumber.getText().toString();
                if (!input.isEmpty()) {
                    int number = Integer.parseInt(input);
                    addNumber(number);
                    editTextNumber.setText("");
                }
            }
        });

        findViewById(R.id.buttonSort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortNumbers();
            }
        });
    }

    private void addNumber(int number) {
        numbers.add(number);
    }

    private void sortNumbers() {
        Collections.sort(numbers);
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(", ");
        }
        String sortedNumbers = sb.toString();
        if (!sortedNumbers.isEmpty()) {
            sortedNumbers = sortedNumbers.substring(0, sortedNumbers.length() - 2);
        }
        textViewNumbers.setText(sortedNumbers);
    }
}
