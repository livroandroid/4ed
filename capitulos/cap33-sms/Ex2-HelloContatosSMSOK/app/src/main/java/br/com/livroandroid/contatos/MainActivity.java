package br.com.livroandroid.contatos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.livroandroid.contatos.agenda.Agenda;

/**
 * Contatos e SMS
 *
 * @author rlecheta
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, new ListaContatosFragment()).commit();
        }
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
            Toast.makeText(this, "Contatos adicionados com sucesso.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}