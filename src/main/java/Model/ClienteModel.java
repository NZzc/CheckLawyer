package Model;

public class ClienteModel {
    private String nome;
    private final String cpf_cnpj;
    private String telefone;
    private String email;
    private EnderecoModel endereco;
    private final int ID;
    private static int geraID;


    public ClienteModel(String nome, String cpf, String telefone, String email, EnderecoModel endereco) {
        this.nome = nome;
        this.cpf_cnpj = cpf;
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

    public String getCpf_cnpj() {
        return cpf_cnpj;
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
