package br.com.livroandroid.carros.activity.prefs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import br.com.livroandroid.carros.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ConfiguracoesV11Activivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PrefsFragment())
                .commit();

        Toast.makeText(this, " isCheckPushOn: " + PrefsUtils.isCheckPushOn(this), Toast.LENGTH_SHORT).show();
    }

    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
