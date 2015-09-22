package br.com.livroandroid.contatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.contatos.R;
import br.com.livroandroid.contatos.agenda.Agenda;
import br.com.livroandroid.contatos.exemplo1.ListaContatosActivity;

/**
 * Exemplos de Layouts
 *
 * @author rlecheta
 *
 */
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        String[] items = new String[] {
                "Lista de Contatos - simples",
                "Lista de Contatos - SimpleCursorAdapter",
                "Lista de Contatos - Custom CursorAdapter",
                "Lista de Contatos - CursorLoader",
        };

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Agenda a = new Agenda(this);
            a.addContato("Mickey","999999999",R.drawable.mickey);
            a.addContato("Pateta","888888888",R.drawable.pateta);
            a.addContato("Donald","777777777",R.drawable.donald);
            Toast.makeText(this,"Contatos adicionados com sucesso.",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    startActivity(new Intent(getBaseContext(), ListaContatosActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getBaseContext(), br.com.livroandroid.contatos.exemplo2.ListaContatosActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getBaseContext(), br.com.livroandroid.contatos.exemplo3.ListaContatosActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getBaseContext(), br.com.livroandroid.contatos.exemplo4.ListaContatosActivity.class));
                    break;
                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}