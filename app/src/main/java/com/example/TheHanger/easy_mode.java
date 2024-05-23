package com.example.TheHanger;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class easy_mode extends AppCompatActivity {

    private EditText displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy);

        // Initialize the EditText
        displayText = findViewById(R.id.displayText);
    }

    // Method to handle button clicks
    public void onKeyboardButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        // Append the text of the button to the existing text in the EditText
        displayText.append(buttonText);
    }


    // Method to handle delete button click
    public void onDeleteButtonClick(View view) {
        // Get the current text from the EditText
        String currentText = displayText.getText().toString();

        // Remove the last character from the text
        if (currentText.length() > 0) {
            String newText = currentText.substring(0, currentText.length() - 1);
            displayText.setText(newText);
        }
    }

}

