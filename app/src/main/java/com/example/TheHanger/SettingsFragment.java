package com.example.TheHanger;

import com.example.helloworld.R;
import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

    public class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }
    }

