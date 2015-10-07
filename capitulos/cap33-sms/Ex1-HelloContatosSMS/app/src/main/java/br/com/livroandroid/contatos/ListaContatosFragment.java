package br.com.livroandroid.contatos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.contatos.adapter.ContatoCursorAdapter;
import br.com.livroandroid.contatos.agenda.Agenda;
import br.com.livroandroid.contatos.agenda.Contato;
import livroandroid.lib.utils.IntentUtils;

/**
 * Fragment que mostra a lista de contatos
 */
public class ListaContatosFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "livroandroid";
    private ListView listView;
    private CursorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_contatos, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        // Configura o ListView com um adapter
        adapter = new ContatoCursorAdapter(getActivity(), null);
        listView.setAdapter(adapter);

        // Inicia o loader
        getLoaderManager().initLoader(1, null, this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Agenda a = new Agenda(getActivity());
        final Contato c = a.getContatoById(id);
        Toast.makeText(getActivity(), "Contato: " + c.nome, Toast.LENGTH_SHORT).show();
        c.show(getActivity());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Retorna o CursorLoader para carregar os contatos
        Uri uriContatos = ContactsContract.Contacts.CONTENT_URI;
        return new CursorLoader(getActivity(), uriContatos,
                null, ContactsContract.Contacts.HAS_PHONE_NUMBER + " = 1 ", null, ContactsContract.Contacts.DISPLAY_NAME);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Carrega o adapter com o cursor
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Limpa o adapter
        adapter.swapCursor(null);
    }
}
