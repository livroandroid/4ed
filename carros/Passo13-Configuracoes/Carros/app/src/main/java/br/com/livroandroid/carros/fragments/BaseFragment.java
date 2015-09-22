package br.com.livroandroid.carros.fragments;

/**
 * Created by Ricardo Lecheta on 24/01/2015.
 */
public class BaseFragment extends livroandroid.lib.fragment.BaseFragment {

    @Override
    protected boolean isLogLifecycle() {
        return true;
    }

    @Override
    protected boolean isLogOn() {
        return false;
    }
}
