package Model;

import jakarta.persistence.*;

import java.util.Comparator;

@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente", discriminatorType = DiscriminatorType.STRING)
public abstract class ClienteModel implements Comparable<ClienteModel>{

    //chave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String observacao;

    //classe endereco fica direto na tabela cliente
    @Embedded
    private EnderecoModel endereco;

    public ClienteModel() {} //construtor vazio pro JPA

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

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getObservacao() {
        return observacao;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao.toUpperCase();
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
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
