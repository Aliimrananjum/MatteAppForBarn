package com.example.matteoppgavebeta;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        //getView().setBackgroundResource(R.drawable.bare_backround);


        //ved endring av språk
        //Her lages det en instans av nSharedPreferenceChangeListener() som hører etter endringene
        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

            //hvis det er endringer i language_preference i listprefereces så trigges denne
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("language_preference")) {
                    updateLocale(sharedPreferences);
                }
            }
        };

        //ved oppstart
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(getContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        updateLocale(sharedPreferences);

    }
    //metoden for å gjøre endringene
    private void updateLocale(SharedPreferences sharedPreferences) {
        String questionLanguage = sharedPreferences.getString("language_preference", "no");

        LocaleListCompat appLocale;

        if (questionLanguage.equals("de")) {
            appLocale = LocaleListCompat.forLanguageTags("de-DE"); // For German
        } else {
            appLocale = LocaleListCompat.forLanguageTags("nb-NO"); // For Norwegian (default)
        }

        AppCompatDelegate.setApplicationLocales(appLocale);
    }

    //bytter bakgrundsfarge på instiller
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundResource(R.drawable.bare_backround);
    }

}