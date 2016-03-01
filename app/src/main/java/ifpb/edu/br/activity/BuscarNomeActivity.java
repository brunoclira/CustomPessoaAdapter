package ifpb.edu.br.activity;

/**
 * Created by Bruno on 01/03/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ifpb.edu.br.adapter.PessoasCustomAdapter;
import ifpb.edu.br.asynctask.BuscarNomeAsyncTask;
import ifpb.edu.br.callback.BuscarPessoaCallBack;
import ifpb.edu.br.custompessoaadapter.R;
import ifpb.edu.br.entidade.Pessoa;

public class BuscarNomeActivity extends Activity
        implements TextWatcher, OnItemClickListener, BuscarPessoaCallBack {

    // Define o tamanho mínimo do texto para consulta no servidor.
    private static int TAMANHO_MINIMO_TEXTO = 4;

    private EditText nomeEditText;
    List<Pessoa> pessoas;
    PessoasCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Inicialização da activity e definição do layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recuperando o EditText e
        nomeEditText = (EditText) findViewById(R.id.nomeEditText);
        nomeEditText.addTextChangedListener(this);

        ListView nomesListView = (ListView) findViewById(R.id.nomesListView);
        pessoas = new ArrayList<Pessoa>();
        adapter = new PessoasCustomAdapter(this, pessoas);

        // Adapter modificado.
        nomesListView.setAdapter(adapter);

        // Evento de OnItemClickListener.
        nomesListView.setOnItemClickListener(this);
    }

    // TextWatcher
    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        Log.i("EditTextListener", "beforeTextChanged: " + charSequence);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        Log.i("EditTextListener", "onTextChanged: " + charSequence);
        String nome = charSequence.toString();

        // Consultar o servidor. Criar o JSONObject e uma AsyncTask<JSONObject, Void, Response>
        try {

            if (nome.length() >= TAMANHO_MINIMO_TEXTO) {
                // JSON
                JSONObject json = new JSONObject();
                json.put("fullName", nome);

                BuscarNomeAsyncTask buscarNomeAsyncTask = new BuscarNomeAsyncTask(this);
                buscarNomeAsyncTask.execute(json);
            }

        } catch (JSONException e) {

            Log.e("EditTextListener", e.getMessage());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

        Log.i("EditTextListener","afterTextChanged: " + editable);
    }

    // OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Log.i("EditTextListener", "Position: " + position);

        Toast toast = Toast.makeText(this,
                "Item " + (position + 1) + ": " + pessoas.get(position),
                Toast.LENGTH_LONG);
        toast.show();
    }

    // BuscarPessoaCallBack
    @Override
    public void backBuscarNome(List<Pessoa> pessoas) {

        this.pessoas.clear();
        this.pessoas.addAll(pessoas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void errorBuscarNome(String error) {

        pessoas.clear();
        adapter.notifyDataSetChanged();

        Toast.makeText(this, error, Toast.LENGTH_LONG);
    }
}