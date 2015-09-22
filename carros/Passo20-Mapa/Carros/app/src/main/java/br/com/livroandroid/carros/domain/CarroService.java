package br.com.livroandroid.carros.domain;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import livroandroid.lib.utils.HttpHelper;

public class CarroService {
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";
    private static final boolean LOG_ON = false;
    private static final String TAG = "CarroService";

    public static List<Carro> getCarros(Context context, String tipo) throws IOException {
        // Por padrão deixa fazer cache
        return getCarros(context, tipo, false);
    }

    public static List<Carro> getCarros(Context context, String tipo, boolean refresh) throws IOException {
        CarroDB db = new CarroDB(context);
        if (!refresh) {
            // Consulta no banco de dados
            List<Carro> carros = db.findAllByTipo(tipo);
            if (carros != null && carros.size() > 0) {
                Log.d(TAG, "Retornando " + carros.size() + " carros [" + tipo + "] do banco");
                for (Carro c: carros) {
                    Log.d(TAG,c.nome + " - "  +c.tipo);
                }
                // Se existe retorna os carros salvos
                return carros;
            }
        }

        // Caso não existe no banco de dados busca no web service
        String url = URL.replace("{tipo}", tipo);
        Log.d(TAG, "Carros [" + tipo + "]: " + url);
        String json = HttpHelper.doGet(url);
        List<Carro> carros = parserJSON(context, json);

        // Deleta os carros pelo tipo para limpar o banco
        db.deleteCarrosByTipo(tipo);

        // Salva todos os carros
        for (Carro c : carros) {
            c.tipo = tipo;
            db.save(c);
        }

        Log.d(TAG, "Carros salvos no banco");

        return carros;
    }

    private static List<Carro> parserJSON(Context context, String json) throws IOException {
        List<Carro> carros = new ArrayList<Carro>();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject obj = root.getJSONObject("carros");
            JSONArray jsonCarros = obj.getJSONArray("carro");
            // Insere cada carro na lista
            for (int i = 0; i < jsonCarros.length(); i++) {
                JSONObject jsonCarro = jsonCarros.getJSONObject(i);
                Carro c = new Carro();
                // Lê as informações de cada carro
                c.nome = jsonCarro.optString("nome");
                c.desc = jsonCarro.optString("desc");
                c.urlFoto = jsonCarro.optString("url_foto");
                c.urlInfo = jsonCarro.optString("url_info");
                c.urlVideo = jsonCarro.optString("url_video");
                c.latitude = jsonCarro.optString("latitude");
                c.longitude = jsonCarro.optString("longitude");
                if (LOG_ON) {
                    Log.d(TAG, "Carro " + c.nome + " > " + c.urlFoto + ", lat/lng " + c.latitude+"/"+c.longitude);
                }
                carros.add(c);
            }
            if (LOG_ON) {
                Log.d(TAG, carros.size() + " encontrados.");
            }
        } catch (JSONException e) {
            throw new IOException(e.getMessage(), e);
        }
        return carros;
    }
}
