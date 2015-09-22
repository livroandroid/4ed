package br.com.livroandroid.carros.domain;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import livroandroid.lib.utils.FileUtils;
import livroandroid.lib.utils.HttpHelper;
import livroandroid.lib.utils.IOUtils;
import livroandroid.lib.utils.SDCardUtils;

public class CarroService {
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";
    private static final boolean LOG_ON = false;
    private static final String TAG = "CarroService";

    public static List<Carro> getCarros(Context context, String tipo) throws IOException {

        List<Carro> carros = getCarrosFromArquivo(context,tipo);
        if(carros != null && carros.size() > 0) {
            // Retorna os carros lidos do arquivo
            return carros;
        }
        // Se não encontrar busca no web service
        carros = getCarrosFromWebService(context,tipo);
        return carros;
    }

    public static List<Carro> getCarrosFromArquivo(Context context, String tipo) throws IOException {
        String fileName = String.format("carros_%s.json",tipo);
        Log.d(TAG,"Abrindo arquivo: " + fileName);
        String json = FileUtils.readFile(context,fileName,"UTF-8");
        if(json == null) {
            Log.d(TAG,"Arquivo "+fileName+" não encontrado.");
            return null;
        }
        List<Carro> carros = parserJSON(context, json);
        Log.d(TAG,"Carros lidos do arquivo "+fileName+".");
        return carros;
    }

    public static List<Carro> getCarrosFromWebService(Context context, String tipo) throws IOException {
        String url = URL.replace("{tipo}", tipo);
        Log.d(TAG,"URL: " + url);
        String json = HttpHelper.doGet(url);
        salvaArquivoNaMemoriaInterna(context, url, json);
        //salvaArquivoNaMemoriaExterna(context,url,json);
        List<Carro> carros = parserJSON(context, json);
        return carros;
    }

    private static void salvaArquivoNaMemoriaInterna(Context context, String url, String json) {
        String fileName = url.substring(url.lastIndexOf("/")+1);
        // Cria o arquivo, exemplo:
        // /data/data/br.com.livroandroid.carros/files/carros_luxo.json
        File file = FileUtils.getFile(context, fileName);
        // Escreve a string no arquivo
        IOUtils.writeString(file, json);
        Log.d(TAG, "Arquivo salvo: " + file);
    }

    private static void salvaArquivoNaMemoriaExterna(Context context, String url, String json) {
        String fileName = url.substring(url.lastIndexOf("/")+1);
        // Cria o arquivo, exemplo:
        //  /storage/sdcard/Android/data/br.com.livroandroid.carros/files/Download/carros_classicos.json
        File f = SDCardUtils.getPrivateFile(context, fileName, Environment.DIRECTORY_DOWNLOADS);
        IOUtils.writeString(f, json);
        Log.d(TAG, "1) Arquivo privado salvo na pasta downloads: " + f);

        // /storage/sdcard/Download/carros_classicos.json
        f = SDCardUtils.getPublicFile(fileName, Environment.DIRECTORY_DOWNLOADS);
        IOUtils.writeString(f, json);
        Log.d(TAG, "2) Arquivo público salvo na pasta downloads: " + f);
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
                if (LOG_ON) {
                    Log.d(TAG, "Carro " + c.nome + " > " + c.urlFoto);
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
