package br.com.livroandroid.playervideo;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String TAG = "livroandroid";
    //Classe que encapsula o MediaPlayer
    private VideoView videoView;
    private EditText text;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.arquivo);

        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(this));

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
    }
    /**
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View view) {
        try {
            if (view.getId() == R.id.start) {
                String path = text.getText().toString();
                videoView.setVideoURI(Uri.parse(path));
                videoView.start();
            } else if (view.getId() == R.id.pause) {
                videoView.pause();
            } else if (view.getId() == R.id.stop) {
                videoView.stopPlayback();
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
        videoView.stopPlayback();
    }
}
