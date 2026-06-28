package Model;

public class ClienteJuridicoModel extends ClienteModel {
    private String cnpj;

    public ClienteJuridicoModel(String nome, String telefone, String email,String observacao , EnderecoModel endereco, String cnpj) {
        this.cnpj = cnpj;
        super(nome,telefone, email, observacao, endereco);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getDocumento(){
        return cnpj;
    }
}