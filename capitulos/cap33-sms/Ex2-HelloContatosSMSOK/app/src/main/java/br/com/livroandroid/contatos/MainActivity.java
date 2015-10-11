package br.com.livroandroid.contatos;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
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

        // Solicita as permissões
        String[] permissoes = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
        };
        boolean ok = PermissionUtils.validate(this, 0, permissoes);

        if(ok) {
            if (savedInstanceState == null) {
                showFragment();
            }
        }
    }

    private void showFragment() {
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new ListaContatosFragment()).commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Alguma permissão foi negada, agora é com você :-)
                alertAndFinish(permissions[result]);
                return;
            }
        }
        // Se chegou aqui está OK :-)
        showFragment();
    }

    private void alertAndFinish(String s) {
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name).setMessage("Para utilizar este aplicativo, você precisa aceitar as permissões ["+s+"].");
            // Add the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            android.support.v7.app.AlertDialog dialog = builder.create();
            dialog.show();

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