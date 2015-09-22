package br.com.livroandroid.carros.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.TabsAdapter;

/**
 * Fragment que controla as Tabs dos carros (classicos,esportivos,luxo)
 */
public class CarrosTabFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carros_tab, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new TabsAdapter(getContext(), getChildFragmentManager()));

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        int cor = getContext().getResources().getColor(R.color.white);
        // Cor branca no texto (fundo azul foi definido no layout)
        tabLayout.setTabTextColors(cor, cor);
        // Adiciona as tabs.
        tabLayout.addTab(tabLayout.newTab().setText(R.string.classicos));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.esportivos));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.luxo));
        // Listener para tratar eventos de clique na tab.
        tabLayout.setOnTabSelectedListener(this);

        // Se mudar o ViewPager atualiza a tab selecionada.
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        return view;
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // Se alterar a tab, atualiza o ViewPager
        mViewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) { }
    @Override
    public void onTabReselected(TabLayout.Tab tab) { }
}