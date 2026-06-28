package Model;

public abstract class ClienteModel {
    private String nome;
    private String telefone;
    private String email;
    private EnderecoModel endereco;
    private String observacao;
    private final int ID;
    private static int geraID;


    public ClienteModel(String nome, String telefone, String email,String observacao , EnderecoModel endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.observacao = observacao;
        this.ID = ++geraID;
    }

    public int getID() {
        return ID;
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

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public abstract String getDocumento();
}
