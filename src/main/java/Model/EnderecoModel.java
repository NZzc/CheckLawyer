package Model;

public class EnderecoModel {
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public EnderecoModel(String rua, int numero, String bairro, String cidade, String uf, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
}
