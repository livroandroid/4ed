package br.com.livroandroid.hellomaterial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;


public class PlanetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planeta);

        int imgPlaneta = getIntent().getIntExtra("imgPlaneta", 0);
        if (imgPlaneta > 0) {
            ImageView img = (ImageView) findViewById(R.id.img);
            img.setImageResource(imgPlaneta);
        }
    }
}
