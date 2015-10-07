package br.com.livroandroid.contatos;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Usuário on 01/04/2015.
 */
public class Sms {
    private static final String TAG = "livro";
    // Envia um sms para o numero indicado
    public void send(Context context, String to, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
            smsManager.sendTextMessage(to, null, msg, pIntent, null);
            Log.d(TAG,"Sms.send to["+to+"] msg["+msg+"]");
        } catch (Exception e) {
            Log.e(TAG, "Erro ao enviar o SMS: " + e.getMessage(),e);
        }
    }
    // Lê uma mensagem da Intent. A Intent é recebida por um IntentFilter
    public SmsMessage read(Intent intent) {
        SmsMessage[] mensagens = getMessagesFromIntent(intent);
        if (mensagens != null) {
            return mensagens[0];
        }
        return null;
    }
    private SmsMessage[] getMessagesFromIntent(Intent intent) {
        Log.d(TAG, "Sms.getMessagesFromIntent: " + intent.getAction());
        Object pdus[] = (Object[]) (Object[]) intent .getSerializableExtra("pdus");
        SmsMessage msgs[] = new SmsMessage[pdus.length];
        for (int i = 0; i < pdus.length; i++) {
            msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            String celular = msgs[0].getDisplayOriginatingAddress();
            String mensagem = msgs[0].getDisplayMessageBody();
        }
        return msgs;
    }
}
