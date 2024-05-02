package com.example.TheHanger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //gedagedigedagedado
        setContentView(R.layout.activity_main);


    }
}
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play1); // bien mettre le bon layout ptn

        // Trouver le bouton avec l'ID "button"
        Button playButton = findViewById(@+id/button);

        // Ajouter un écouteur de clics au bouton
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer une nouvelle activité
                Intent intent = new Intent(MainActivity.this, play1.class);
                startActivity(intent);
            }
        });
    }
}