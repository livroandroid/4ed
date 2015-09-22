package br.com.livroandroid.playermp3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import livroandroid.lib.utils.NotificationUtil;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "livro";
    // Classe que encapsula o MediaPlayer

    private EditText text;

    private InterfaceMp3 interfaceMp3;
    private ServiceConnection conexao = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // (*3*)
            // Recupera a interface para interagir com o serviço
            Mp3Service.Mp3ServiceBinder conexao = (Mp3Service.Mp3ServiceBinder) service;
            interfaceMp3 = conexao.getInterface();
            Log.d(TAG, "onServiceConnected, interfaceMp3 conectada: " + interfaceMp3);
        }
        public void onServiceDisconnected(ComponentName className) {
            // (*6*)
            Log.d(TAG, "onServiceDisconnected, liberando recursos.");
            interfaceMp3 = null;
        }
    };
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.tArquivo);

        Intent intent = new Intent(this,Mp3Service.class);
        Log.d(TAG, "Iniciando o service");
        // (*1*)
        startService(intent);
        // Faz o bind/ligação
        // (*2*)
        boolean b = bindService(intent, conexao, Context.BIND_AUTO_CREATE);
        Log.d(TAG,"Service conectado: " + b);
    }
    public void onClickPlay(View view) {
        // (*4*)
        if(interfaceMp3 != null) {
            String mp3 = text.getText().toString();
            Log.d(TAG,"play: " +  mp3);
            interfaceMp3.play(mp3);
        }
    }
    public void onClickPause(View view) {
        // (*4*)
        if(interfaceMp3 != null) {
            Log.d(TAG,"pause");
            interfaceMp3.pause();
        }
    }
    public void onClickStop(View view) {
        // (*4*)
        if(interfaceMp3 != null) {
            Log.d(TAG, "stop");
            interfaceMp3.stop();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(interfaceMp3 != null && interfaceMp3.isPlaying()) {
            // (*5*)
            Log.d(TAG, "Activity destruida. A música continua.");
            unbindService(conexao);
            // Cria a notificação para o usuário voltar ao player.
            String mp3 = interfaceMp3.getMp3();
            NotificationUtil.create(this, 1, new Intent(this, MainActivity.class),R.mipmap.ic_launcher, "MP3 Player", mp3);
        } else {
            // (*7*)
            Log.d(TAG, "Activity destruida. Para o serviço, pois não existe música tocando.");
            unbindService(conexao);
            stopService(new Intent(this, Mp3Service.class));
        }
    }
}
