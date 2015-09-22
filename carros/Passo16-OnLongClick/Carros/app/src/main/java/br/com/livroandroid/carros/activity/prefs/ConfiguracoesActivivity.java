package br.com.livroandroid.carros.activity.prefs;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import br.com.livroandroid.carros.R;

@SuppressWarnings("ALL")
public class ConfiguracoesActivivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Toast.makeText(this, "isCheckPushOn: " + PrefsUtils.isCheckPushOn(this), Toast.LENGTH_SHORT).show();
    }
}
