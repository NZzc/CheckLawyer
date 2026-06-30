package Model;

import jakarta.persistence.*;

import java.util.Comparator;

@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente", discriminatorType = DiscriminatorType.STRING)
public abstract class ClienteModel implements Comparable<ClienteModel>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    //entidade endereço fica direto na tabela cliente
    @Embedded
    private EnderecoModel endereco;

    public ClienteModel() {
    }

    public ClienteModel(String nome, String telefone, String email, String observacao, EnderecoModel endereco) {
        this.nome = nome.toUpperCase();
        this.telefone = telefone;
        this.email = email;
        this.observacao = observacao.toUpperCase();
        this.endereco = endereco;
    }

    public int getID() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String t) {
        this.telefone = t;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String o) {
        this.observacao = o;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel e) {
        this.endereco = e;
    }

    public abstract String getDocumento();

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " (" + getDocumento() + ")";
    }

    @Override
    public int compareTo(ClienteModel outro) {
        return this.nome.compareTo(outro.nome);
    }

    public static final Comparator<ClienteModel> POR_ID = Comparator.comparingInt(ClienteModel::getID);

}
