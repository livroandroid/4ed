package br.com.livroandroid.intents;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Activity simples com um bot�o na tela e deixa a Activity filha implementar o m�todo onClick()
 *
 * @author ricardo
 */
public abstract class BaseButtonActivity extends ActionBarActivity implements OnClickListener {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        //cria o layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(layoutParams);

        Button button = new Button(this);
        button.setText(getTexto());
        button.setOnClickListener(this);

        layout.addView(button, layoutParams);

        setContentView(layout);
    }

    protected abstract String getTexto();

    public abstract void onClick(View view);
}
