package br.com.livroandroid.carros.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.carros.CarrosApplication;
import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.activity.VideoActivity;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.fragments.dialog.DeletarCarroDialog;
import br.com.livroandroid.carros.fragments.dialog.EditarCarroDialog;
import livroandroid.lib.utils.IntentUtils;

public class CarroFragment extends BaseFragment {
    private Carro carro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carro, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_frag_carro, menu);

//        MenuItem shareItem = menu.findItem(R.id.action_share);
//        ShareActionProvider share = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            //toast("Editar: " + carro.nome);
            EditarCarroDialog.show(getFragmentManager(), carro, new EditarCarroDialog.Callback() {
                @Override
                public void onCarroUpdated(Carro carro) {
                    toast("Carro [" + carro.nome + "] atualizado.");
                    CarrosApplication.getInstance().setPrecisaAtualizar(carro.tipo, true);
                    // Atualiza o título com o novo nome
                    getActionBar().setTitle(carro.nome);
                }
            });
            return true;
        } else if (item.getItemId() == R.id.action_remove) {
            //toast("Deletar: " + carro.nome);

            DeletarCarroDialog.show(getFragmentManager(), carro, new DeletarCarroDialog.Callback() {
                @Override
                public void onCarroDeleted(Carro carro) {
                    toast("Carro [" + carro.nome + "] deletado.");
                    CarrosApplication.getInstance().setPrecisaAtualizar(carro.tipo, true);
                    // Fecha a activity
                    getActivity().finish();
                }
            });

            return true;
        } else if (item.getItemId() == R.id.action_share) {
            toast("Compartilhar");
        } else if (item.getItemId() == R.id.action_maps) {
            toast("Mapa");
        } else if (item.getItemId() == R.id.action_video) {
            // URL do vídeo
            final String url = carro.urlVideo;
            toast("URL: " + url);
            // Lê a view que é a âncora do popup
            View menuItemView = getActivity().findViewById(item.getItemId());
            if (menuItemView != null && url != null) {
                // Cria o PopupMenu posicionado na âncora
                PopupMenu popupMenu = new PopupMenu(getActionBar().getThemedContext(), menuItemView);
                popupMenu.inflate(R.menu.menu_popup_video);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_video_browser) {
                            // Abre o vídeo no browser
                            IntentUtils.openBrowser(getContext(), url);
                        } else if (item.getItemId() == R.id.action_video_player) {
                            // Abre o vídeo no Player de Vídeo Nativo
                            IntentUtils.showVideo(getContext(), url);
                        } else if (item.getItemId() == R.id.action_video_videoview) {
                            // Abre outra activity com VideoView
                            Intent intent = new Intent(getContext(), VideoActivity.class);
                            intent.putExtra("carro", carro);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // Método público chamado pela activity para atualizar os dados do carro
    public void setCarro(Carro carro) {
        if(carro != null) {
            this.carro = carro;

            //setTextString(R.id.tNome,carro.nome);
            setTextString(R.id.tDesc, carro.desc);

            final ImageView imgView = (ImageView) getView().findViewById(R.id.img);

            Picasso.with(getContext()).load(carro.urlFoto).fit().into(imgView);
        }
    }
}