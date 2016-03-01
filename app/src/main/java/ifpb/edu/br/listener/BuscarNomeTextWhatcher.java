package ifpb.edu.br.listener;

/**
 * Created by Bruno on 01/03/2016.
 */
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by Rhavy on 24/02/2016.
 */
public class BuscarNomeTextWhatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        Log.i("EditTExtListener", "beforeTextChanged: " + charSequence);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        Log.i("EditTExtListener","onTextChanged: " + charSequence);


    }

    @Override
    public void afterTextChanged(Editable editable) {
        Log.i("EditTExtListener","afterTextChanged: " + editable);
    }
}
