package com.example.TheHanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import com.example.helloworld.R;

public class MainActivity extends AppCompatActivity {

    // Initialize your custom keyboard layout
    LinearLayout keyboardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the custom keyboard layout by its ID
        keyboardLayout = findViewById(R.id.keyboardLayout);
    }

    // Define a method to handle button clicks on the custom keyboard
    public void onKeyboardButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        // Handle button click according to its text or perform any action
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //
        setContentView(R.layout.activity_main);

        // Trouver le bouton avec l'ID "button"
        Button playButton = findViewById(R.id.play);

        // Ajouter un écouteur de clics au bouton
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer une nouvelle activité
                Intent intent = new Intent(MainActivity.this, select_difficulty.class);
                startActivity(intent);
            }
        });
    }
}