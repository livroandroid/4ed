package br.com.livroandroid.carros.fragments;

import android.app.backup.BackupManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.TabsAdapter;
import livroandroid.lib.utils.Prefs;
import livroandroid.lib.utils.PrefsFile;

/**
 * Fragment que controla as Tabs dos carros (classicos,esportivos,luxo)
 */
public class CarrosTabFragment extends BaseFragment {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager viewPager;
    private BackupManager backupManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carros_tab, container, false);

        // Gerenciador de backup
        backupManager = new BackupManager(getContext());

        // ViewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(getContext(), getChildFragmentManager()));

        // Tabs
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_layout, R.id.tabText);
        // Deixa as tabs com mesmo tamanho (layout_weight=1)
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(viewPager);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int i) {
                // Cor do indicador da tab
                return getResources().getColor(R.color.accent);
            }
        });
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // Salva o índice da página/tab selecionada
                PrefsFile.setInteger(getContext(), "tabIdx", viewPager.getCurrentItem());
                backupManager.dataChanged();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // Inicia o aplicativo com o índice da última tab/página selecionada
        int tabIdx = PrefsFile.getInteger(getContext(), "tabIdx");
        viewPager.setCurrentItem(tabIdx);
        return view;
    }
}