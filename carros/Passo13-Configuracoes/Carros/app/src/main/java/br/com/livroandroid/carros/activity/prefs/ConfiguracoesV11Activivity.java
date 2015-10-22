package br.com.livroandroid.carros.activity.prefs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.activity.BaseActivity;
import livroandroid.lib.utils.AndroidUtils;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ConfiguracoesV11Activivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prefs);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragLayout, new PrefsFragment())
                    .commit();
        }

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
