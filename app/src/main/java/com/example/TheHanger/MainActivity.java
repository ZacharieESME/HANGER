package com.example.TheHanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;


public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        // Pour start la musique
        Intent serviceIntent = new Intent(this, MusicService.class);
        startService(serviceIntent);

        // Register the receiver
        registerReceiver(soundEffectsReceiver, new IntentFilter("com.TheHanger.ACTION_SOUND_EFFECTS_CHANGED"));

        // Check the current preference and apply it
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean soundEffectsEnabled = sharedPreferences.getBoolean("sound_effects", true);
        setSoundEffectsEnabled((ViewGroup) findViewById(android.R.id.content), soundEffectsEnabled);

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

        getSupportActionBar().setTitle("The Hanger");

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

    @Override //Pour le menu des settings
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override //Pour la musique
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the receiver
        unregisterReceiver(soundEffectsReceiver);
        Intent serviceIntent = new Intent(this, MusicService.class);
        stopService(serviceIntent);
    }


}
