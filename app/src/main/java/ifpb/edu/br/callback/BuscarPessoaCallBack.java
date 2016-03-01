package ifpb.edu.br.callback;

/**
 * Created by Bruno on 01/03/2016.
 */
import java.util.List;

import ifpb.edu.br.entidade.Pessoa;

/**
 * Created by Rerisson Daniel on 25/02/16.
 */
public interface BuscarPessoaCallBack {

    void backBuscarNome(List<Pessoa> names);

    void errorBuscarNome(String error);
}
