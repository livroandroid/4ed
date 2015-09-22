package br.com.livroandroid.carros.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CarroService {
    private static final String URL = "http://www.livroiphone.com.br/carros/carros_{tipo}.json";
    private static final boolean LOG_ON = false;
    private static final String TAG = "CarroService";

    public static List<Carro> getCarros(Context context, String tipo) {
        List<Carro> carros = new ArrayList<Carro>();
        for (int i = 0; i < 20; i++) {
            Carro c = new Carro();
            c.nome = "Carro " + tipo + ": " + i;
            c.desc = "Desc " + i;
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";
            carros.add(c);
        }
        return carros;
    }
}
