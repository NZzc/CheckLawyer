package Model;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private EnderecoModel endereco;


    public Cliente(String nome, String cpf, String telefone, String email, EnderecoModel endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }



}
