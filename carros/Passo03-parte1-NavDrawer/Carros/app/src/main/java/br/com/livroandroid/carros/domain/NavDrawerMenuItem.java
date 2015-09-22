package br.com.livroandroid.carros.domain;

/**
 * Created by ricardo on 06/05/15.
 */

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.carros.R;

public class NavDrawerMenuItem {
    // Título: R.string.xxx
    public int title;
    // Figura: R.drawable.xxx
    public int img;
    // Para colocar um fundo cinza quando a linha está selecionada
    public boolean selected;

    public NavDrawerMenuItem(int title, int img) {
        this.title = title;
        this.img = img;
    }

    // Cria a lista com os itens de menu
    public static List<NavDrawerMenuItem> getList() {
        List<NavDrawerMenuItem> list = new ArrayList<NavDrawerMenuItem>();
        list.add(new NavDrawerMenuItem(R.string.carros, R.drawable.ic_drawer_carro));
        list.add(new NavDrawerMenuItem(R.string.site_livro, R.drawable.ic_drawer_site_livro));
        list.add(new NavDrawerMenuItem(R.string.configuracoes, R.drawable.ic_drawer_settings));
        return list;
    }
}