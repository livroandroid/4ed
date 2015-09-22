package br.com.livroandroid.carros.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;

public class CarroFragment extends BaseFragment {
    private Carro carro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carro, container, false);
        return view;
    }

    // Método público chamado pela activity para atualizar os dados do carro
    public void setCarro(Carro carro) {
        this.carro = carro;

        //setTextString(R.id.tNome,carro.nome);
        setTextString(R.id.tDesc, carro.desc);

        final ImageView imgView = (ImageView) getView().findViewById(R.id.img);

        Picasso.with(getContext()).load(carro.urlFoto).fit().into(imgView);
    }
}