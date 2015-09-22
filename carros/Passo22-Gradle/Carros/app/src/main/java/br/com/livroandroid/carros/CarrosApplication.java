package br.com.livroandroid.carros;

import android.app.Application;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ricardo Lecheta on 24/01/2015.
 */
public class CarrosApplication extends Application {
    private static final String TAG = "CarrosApplication";
    // Singleton
    private static CarrosApplication instance = null;

    // Map com o tipo do carro e o flag se precisa atualizar a lista
    private Map<String, Boolean> mapUpdate = new HashMap<String,Boolean>();

    public static CarrosApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "CarrosApplication.onCreate()");
        // Salva a instância para termos acesso como Singleton
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "CarrosApplication.onTerminate()");
    }

    public void setPrecisaAtualizar(String tipo, boolean b) {
        this.mapUpdate.put(tipo, b);
    }

    public boolean isPrecisaAtualizar(String tipo) {
        if(mapUpdate.containsKey(tipo)) {
            boolean b = mapUpdate.remove(tipo);
            return b;
        }
        return false;
    }
}