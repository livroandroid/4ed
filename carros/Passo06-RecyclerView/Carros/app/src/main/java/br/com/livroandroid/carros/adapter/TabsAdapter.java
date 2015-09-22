package br.com.livroandroid.carros.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.fragments.CarrosFragment;

/* Adapter para preencher o ViewPager com fragments */
public class TabsAdapter extends android.support.v4.app.FragmentPagerAdapter {
    private Context context;

    public TabsAdapter(Context context, android.support.v4.app.FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.classicos);
        } else if (position == 1) {
            return context.getString(R.string.esportivos);
        }
        return context.getString(R.string.luxo);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if (position == 0) {
            args.putString("tipo", "classicos");
        } else if (position == 1) {
            args.putString("tipo", "esportivos");
        } else {
            args.putString("tipo", "luxo");
        }
        Fragment f = new CarrosFragment();
        f.setArguments(args);
        return f;
    }
}