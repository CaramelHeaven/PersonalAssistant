package com.volgagas.personalassistant.presentation.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.MenuItem;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {

    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // load settings fragment
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new MainPreferenceFragment())
                .commit();
    }

    public static class MainPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_main);

            sharedPreferences = getActivity().getSharedPreferences(Constants.SP_USER_PREFERENCE, Context.MODE_PRIVATE);

            // bind preferences
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_http_dynamics365)));
            bindPreferenceSwitch(findPreference(getString(R.string.key_enable_all_functions)));
        }

        @Override
        public void onStop() {
            sharedPreferences = null;
            super.onStop();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private static void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private static void bindPreferenceSwitch(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        //Enable if user set it true
        if (sharedPreferences.getBoolean(Constants.SP_ENABLE_FUNCTIONS, false)) {
            preference.setEnabled(true);
        }

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getBoolean(preference.getKey(), false));
    }

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = (preference, newValue) -> {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String stringValue = newValue.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list.
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(stringValue);

            // Set the summary to reflect the new value.
            preference.setSummary(index >= 0
                    ? listPreference.getEntries()[index]
                    : null);

            String[] array = preference.getContext().getResources().getStringArray(R.array.pref_upload_http_references);
            String value = array[Integer.parseInt(stringValue)];

            String referenceHttp = "";
            if (value.contains("tst")) {
                referenceHttp = Constants.DYNAMICS_TEST;
            } else if (value.contains("prod")) {
                referenceHttp = Constants.DYNAMICS_PROD;
            }

            editor.putString(Constants.SP_CURRENT_HTTP, referenceHttp);

            //REFRESH TOKEN
            RxBus.getInstance().passActionForUpdateToken(referenceHttp);

            //Replace our base constant for current url
            Constants.DYNAMICS_365 = referenceHttp;
        } else if (preference instanceof SwitchPreference) {
            editor.putBoolean(Constants.SP_ENABLE_FUNCTIONS, (boolean) newValue);
        }

        editor.commit();

        return true;
    };
}
