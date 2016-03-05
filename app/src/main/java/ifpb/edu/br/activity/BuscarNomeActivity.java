package ifpb.edu.br.activity;

/**
 * Created by Bruno on 01/03/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

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
        implements TextWatcher, BuscarPessoaCallBack {

    // Define o tamanho mínimo do texto para consulta no servidor.
    private static int TAMANHO_MINIMO_TEXTO = 1;

    private EditText nomeEditText;
    ArrayAdapter<String> arrayAdapter;
    List<Pessoa> pessoas;
    PessoasCustomAdapter adapter;
    ListView nomesListView;

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

        /*TESTE
        nomesListView = (ListView) findViewById(R.id.nomesListView);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        nomesListView.setAdapter(arrayAdapter);*/

        // Evento de OnItemClickListener.
        //nomesListView.setOnItemClickListener(this);
    }

    // TextWatcher
    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        Log.i("EditTextListener", "beforeTextChanged: " + charSequence);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        // Consultar o servidor. Criar o JSONObject e uma AsyncTask<JSONObject, Void, Response>

        Log.i("EditTextListener", "onTextChanged: " + charSequence);
        String nome = charSequence.toString();

        if (nome.length() >= TAMANHO_MINIMO_TEXTO) {

            //criação do objeto para demonstrar...
            Pessoa user = new Pessoa();
            //user.getId();
            user.getNome();
            //user.getEmail();
            user.getDescricao();

            //código que faz o trabalho
            Gson gson = new Gson();
            String userJSONString = gson.toJson(user);

            //Para ver o resultado no Logcat
            Log.i("Gson", "user JSON String: " + userJSONString);

            JSONObject json = new JSONObject();
            try {
                json.put("fullName", nome);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //BuscarNomeAsyncTask buscarNomeAsyncTask = new BuscarNomeAsyncTask();
            //buscarNomeAsyncTask.execute(json);

            BuscarNomeAsyncTask buscaAsyncTask = new BuscarNomeAsyncTask(this);
            buscaAsyncTask.execute(json);

           // this.pessoas.addAll(pessoas);
           // adapter.notifyDataSetChanged();
            pessoas.add(user);
            adapter.notifyDataSetChanged();

        } else {
            pessoas.clear();
            arrayAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

        Log.i("EditTextListener","afterTextChanged: " + editable);
    }

    public Pessoa loadUserFromJSONGson(String jsonString) {
        Gson gson = new Gson();
        Pessoa user = gson.fromJson(jsonString, Pessoa.class);
        return user;
    }

    // OnItemClickListener
   /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Log.i("EditTextListener", "Position: " + position);

        Toast toast = Toast.makeText(this,
                "Item " + (position + 1) + ": " + pessoas.get(position),
                Toast.LENGTH_LONG);
        toast.show();
    }*/

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