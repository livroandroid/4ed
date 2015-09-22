package br.com.livroandroid.hellobarcodereader;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Lê um cod barras QR Code
 *
 * http://zxing.appspot.com/generator/
 *
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        findViewById(R.id.btScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                if(IntentUtils.isAvailable(context,intent)) {
                    startActivityForResult(intent, 0);
                } else {
                    Toast.makeText(context,"Instale o app Barcode Scanner",Toast.LENGTH_SHORT).show();

                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.google.zxing.client.android"));
                    startActivity(intent);
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String s = intent.getStringExtra("SCAN_RESULT");
                // Este é o texto do código de barras

                TextView t = (TextView) findViewById(R.id.text);
                t.setText(s);
            }

        }

    }
}
