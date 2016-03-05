package ifpb.edu.br.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ifpb.edu.br.custompessoaadapter.R;

/**
 * Created by Bruno on 05/03/2016.
 */
public class SplashScreen extends Activity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(this, 5000);
    }

    @Override
    public void run() {
        startActivity(new Intent(this, BuscarNomeActivity.class));
        finish();
    }

}
