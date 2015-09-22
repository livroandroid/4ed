package br.com.livroandroid.threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


/**
 * Faz download de uma imagem com a classe AsyncTask
 *
 * @author rlecheta
 */
public class MainActivity extends AppCompatActivity {
    private static final String URL = "http://livroandroid.com.br/imgs/livro_android.png";
    private ProgressBar progress;
    private ImageView imgView;
    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        imgView = (ImageView) findViewById(R.id.img);
        progress = (ProgressBar) findViewById(R.id.progress);
        // Faz o download
        downloadImagem();
    }

    private void downloadImagem() {


        //Picasso.with(this).load(URL).into(imgView);

        /*Picasso.with(this).load(URL)
                .placeholder(R.drawable.android)
                .error(R.drawable.android)
                .into(imgView);*/

        progress.setVisibility(View.VISIBLE);
        Picasso.with(this).load(URL)
                .placeholder(R.drawable.android)
                .error(R.drawable.android)
                .into(imgView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progress.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError() {
                        progress.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            Toast.makeText(this,"Download!",Toast.LENGTH_SHORT).show();
            downloadImagem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Context getContext() {
        return this;
    }
}
