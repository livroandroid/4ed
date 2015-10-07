package br.com.livroandroid.contatos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.text.Html;

/**
 * Created by Usuário on 01/04/2015.
 */
public class IntentUtils {
    public static void sendSms(Context context, String fone,String msg) {
        Uri uri = Uri.parse("sms:"+fone);
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
        intent.putExtra("sms_body", msg);
        context.startActivity(intent);
    }
}
