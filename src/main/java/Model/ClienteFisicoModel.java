package Model;

public class ClienteFisicoModel extends ClienteModel {
    private String cpf;


    public ClienteFisicoModel(String nome, String telefone, String email,String observacao , EnderecoModel endereco,  String cpf) {
        this.cpf = cpf;
        super(nome, telefone, email, observacao, endereco);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getDocumento(){
        return cpf;
    }
}