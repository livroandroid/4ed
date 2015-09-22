package br.com.livroandroid.carros.activity;

import android.os.Bundle;

import br.com.livroandroid.carros.CarrosApplication;

public class BaseActivity extends livroandroid.lib.activity.BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CarrosApplication app = CarrosApplication.getInstance();
    }
}
