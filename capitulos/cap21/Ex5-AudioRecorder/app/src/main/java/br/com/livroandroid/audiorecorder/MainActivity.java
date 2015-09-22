package br.com.livroandroid.audiorecorder;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import livroandroid.lib.utils.IntentUtils;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String TAG = "livroandroid";
    // Caminho para salvar o arquivo
    private Uri uri;
    private PlayerMp3 player = new PlayerMp3();
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);

        text = (TextView) findViewById(R.id.tArquivo);

        final Context context = this;

        findViewById(R.id.btGravar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Esta intent retorna o audio de forma fixa na Uri da Intent de retorno.
                Intent i = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                if(IntentUtils.isAvailable(context,i)) {
                    startActivityForResult(i, 0);
                } else {
                    Toast.makeText(context,"Este dispositivo não possui app de gravar áudio",Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null) {
            // Se girou a tela recupera o estado
            uri = (Uri) savedInstanceState.getParcelable("uri");
            play();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvar o estado caso gire a tela
        outState.putParcelable("uri", uri);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            // Retorna:
            // Uri: content://media/external/audio/media/102
            uri = data.getData();

            //MediaPlayer player = MediaPlayer.create(this, uri);
            //player.start();

            play();
        }
    }

    private void play() {
        Log.d(TAG, "Uri: " +  uri);

        if (uri != null) {
            text.setText(uri.toString());
            Toast.makeText(this,"File: " + uri,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View view) {
        try {
            if (view.getId() == R.id.start) {
                if(uri != null) {
                    player.start(this, uri);
                }
            } else if (view.getId() == R.id.pause) {
                player.pause();
            } else if (view.getId() == R.id.stop) {
                player.stop();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libera recursos do MediaPlayer
        player.close();
    }
}