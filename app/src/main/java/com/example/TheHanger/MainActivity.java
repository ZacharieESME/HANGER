package com.example.TheHanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import com.example.helloworld.R;

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
