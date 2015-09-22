package br.com.livroandroid.carros.backup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.FileBackupHelper;
import android.app.backup.SharedPreferencesBackupHelper;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.IOException;

import br.com.livroandroid.carros.domain.CarroDB;
import livroandroid.lib.utils.Prefs;

/**
 * Para fazer o backup no emulador digite os seguintes comandos.
 *
adb shell bmgr enable true
adb shell bmgr backup br.com.livroandroid.carros
adb shell bmgr run
adb uninstall br.com.livroandroid.carros
 */
public class ExemploBackupAgent extends BackupAgentHelper {
    @Override
    public void onCreate() {
        // Cria um helper. A string "LivroAndroid" é a mesma chave utilizada pelo SharedPreferences
        SharedPreferencesBackupHelper helper = new
                SharedPreferencesBackupHelper(this, Prefs.PREF_ID);
        // Adiciona o helper ao agente de backups
        addHelper("livroAndroid", helper);

        // Também podemos criar um FileBackupHelper para fazer backup de arquivos
        FileBackupHelper file = new FileBackupHelper(this,"tabIdx.txt");
        addHelper("livroAndroid-file", file);
    }
    @Override
    public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data,
                         ParcelFileDescriptor newState) throws IOException {
        super.onBackup(oldState, data, newState);
        Log.d("backup","Backup efetuado com sucesso.");
    }
    @Override
    public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState)
            throws IOException {
        super.onRestore(data, appVersionCode, newState);
        Log.d("backup","Backup restaurado com sucesso.");
    }
}