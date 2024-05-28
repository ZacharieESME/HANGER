package com.example.TheHanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

public class select_difficulty extends AppCompatActivity {

    private BroadcastReceiver soundEffectsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.TheHanger.ACTION_SOUND_EFFECTS_CHANGED".equals(intent.getAction())) {
                boolean soundEffectsEnabled = intent.getBooleanExtra("sound_effects_enabled", true);
                setSoundEffectsEnabled((ViewGroup) findViewById(android.R.id.content), soundEffectsEnabled);
            }
        }
    }; //Pour les sound effect on a besoin d'un receiver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //
        setContentView(R.layout.select_difficulty);

        // Trouver le bouton avec l'ID "button"
        Button easyButton = findViewById(R.id.easy);

        // Register the receiver
        registerReceiver(soundEffectsReceiver, new IntentFilter("com.TheHanger.ACTION_SOUND_EFFECTS_CHANGED"));

        // Check the current preference and apply it
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean soundEffectsEnabled = sharedPreferences.getBoolean("sound_effects", true);
        setSoundEffectsEnabled((ViewGroup) findViewById(android.R.id.content), soundEffectsEnabled);

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



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the receiver
        unregisterReceiver(soundEffectsReceiver);
    }

    private void setSoundEffectsEnabled(ViewGroup viewGroup, boolean enabled) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                setSoundEffectsEnabled((ViewGroup) view, enabled);
            } else {
                view.setSoundEffectsEnabled(enabled);
            }
        }
    }
}