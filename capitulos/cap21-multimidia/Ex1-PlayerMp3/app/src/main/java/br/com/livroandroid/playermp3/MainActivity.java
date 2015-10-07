package br.com.livroandroid.playermp3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "livro";
    // Classe que encapsula o MediaPlayer
    private PlayerMp3 player = new PlayerMp3();
    private EditText text;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.tArquivo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libera recursos do MediaPlayer
        player.close();
    }

    public void onClickStart(View view) {
        String mp3 = text.getText().toString();
        player.start(mp3);
    }

    public void onClickPause(View view) {
        player.pause();
    }

    public void onClickStop(View view) {
        player.stop();
    }
}
