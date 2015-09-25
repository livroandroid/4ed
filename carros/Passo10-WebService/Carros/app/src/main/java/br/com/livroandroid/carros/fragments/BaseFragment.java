package br.com.livroandroid.carros.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by Ricardo Lecheta on 24/01/2015.
 */
public class BaseFragment extends livroandroid.lib.fragment.BaseFragment {

    @Override
    protected boolean isLogLifecycle() {
        return false;
    }

    @Override
    protected boolean isLogOn() {
        return false;
    }
}
