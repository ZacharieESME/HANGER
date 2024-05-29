package com.example.TheHanger;

import com.example.helloworld.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;
import androidx.preference.PreferenceManager;
import android.content.Intent;
import android.util.Log;


public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "SettingsFragment";


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("volume")) {
            int volume = sharedPreferences.getInt(key, 50);
            Log.d(TAG, "Volume changed to: " + volume); // Log the volume change
            // Communicate the volume change to the MusicService
            Intent intent = new Intent(getActivity(), MusicService.class);
            intent.putExtra("volume", volume);
            getActivity().startService(intent);
        }
        if (key.equals("sound_effects")) {
            boolean soundEffectsEnabled = sharedPreferences.getBoolean(key, true);
            Log.d(TAG, "Sound effects enabled: " + soundEffectsEnabled);
            // Send broadcast to notify activities and fragments about the preference change
            Intent intent = new Intent("com.example.ACTION_SOUND_EFFECTS_CHANGED");
            intent.putExtra("sound_effects_enabled", soundEffectsEnabled);
            getActivity().sendBroadcast(intent);
        }
    }


}
