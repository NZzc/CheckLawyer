package Model;

public class EnderecoModel {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public EnderecoModel(String rua, String numero, String bairro, String cidade, String uf, String cep) {
        this.rua = rua.toUpperCase();
        this.numero = numero.toUpperCase();
        this.bairro = bairro.toUpperCase();
        this.cidade = cidade.toUpperCase();
        this.uf = uf.toUpperCase();
        this.cep = cep.toUpperCase();
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }


    public String getBairro() {
        return bairro;
    }


    public String getCidade() {
        return cidade;
    }


    public String getUf() {
        return uf;
    }


    public String getCep() {
        return cep;
    }


    @Override
    public String toString() {
        return "rua " + rua + ", numero " + numero + ", bairro " + bairro + ", cidade " + cidade + "- uf " + uf + ", cep " + cep + "\n";
    }
}
