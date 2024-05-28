package com.example.TheHanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class select_difficulty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //
        setContentView(R.layout.select_difficulty);

        // Trouver le bouton avec l'ID "button"
        Button easyButton = findViewById(R.id.easy);

        // Ajouter un écouteur de clics au bouton
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer une nouvelle activité
                Intent intent = new Intent(select_difficulty.this, easy_mode.class);
                startActivity(intent);
            }
        });

        // Trouver le bouton avec l'ID "button"
        Button mediumButton = findViewById(R.id.medium);

        // Ajouter un écouteur de clics au bouton
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer une nouvelle activité
                Intent intent = new Intent(select_difficulty.this, medium_mode.class);
                startActivity(intent);
            }
        });

        // Trouver le bouton avec l'ID "button"
        Button hardButton = findViewById(R.id.hard);

        // Ajouter un écouteur de clics au bouton
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer une nouvelle activité
                Intent intent = new Intent(select_difficulty.this, hard_mode.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setTitle("The Hanger");
        getSupportActionBar().hide();



    }
}