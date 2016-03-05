package ifpb.edu.br.entidade;

/**
 * Created by Bruno on 01/03/2016.
 */
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Rhavy on 27/02/2016.
 */
public class Pessoa implements Serializable {


    public Pessoa () {

    }

    @SerializedName("id")
    private int id;

    @SerializedName("fullName")
    private String nome;

    @SerializedName("email")
    private String email;

    @SerializedName("typeInscription")
    private String descricao;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public boolean isEntregue() {

        return entregue;
    }

    public void setEntregue(boolean entregue) {

        this.entregue = entregue;
    }

    @SerializedName("isDelivered")
    private boolean entregue;

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    @Override
    public String toString() {//email - email
        return "[fullName: " + nome + "; typeInscription: " + descricao + "]";
    }
}
