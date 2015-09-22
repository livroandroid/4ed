package br.com.livroandroid.hellodesignsupportlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;


public class PlanetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planeta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        int imgPlaneta = getIntent().getIntExtra("imgPlaneta", 0);
        if (imgPlaneta > 0) {
            ImageView img = (ImageView) findViewById(R.id.img);
            img.setImageResource(imgPlaneta);
        }
    }
}
