package Model;

public class ClienteModel {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private EnderecoModel endereco;
    private int ID;
    private static int geraID;


    public ClienteModel(String nome, String cpf, String telefone, String email, EnderecoModel endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.ID = ++geraID;
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
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
}
