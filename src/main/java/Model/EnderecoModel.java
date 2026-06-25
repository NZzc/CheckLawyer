package Model;

public class EnderecoModel {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public EnderecoModel(String rua, String  numero, String bairro, String cidade, String uf, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public boolean setRua(String rua) {
        if(rua.isEmpty()){
            return false;
        }
        this.rua = rua.toUpperCase();
        return true;
    }

    public String getNumero() {
        return numero;
    }

    public boolean setNumero(String numero) {
        if(numero.isEmpty()){
            return false;
        }
        this.numero = numero;
        return true;
    }

    public String getBairro() {
        return bairro;
    }

    public boolean setBairro(String bairro) {
        if(bairro.isEmpty()){
            return false;
        }
        this.bairro = bairro.toUpperCase();
        return true;
    }

    public String getCidade() {
        return cidade;
    }

    public boolean setCidade(String cidade) {
        if(cidade.isEmpty()){
            return false;
        }
        this.cidade = cidade.toUpperCase();
        return true;
    }

    public String getUf() {
        return uf;
    }

    public boolean setUf(String uf) {
        if(uf.isEmpty()){
            return false;
        }
        this.uf = uf.toUpperCase();
        return true;
    }

    public String getCep() {
        return cep;
    }

    public boolean setCep(String cep) {
        if(cep.isEmpty()){
            return false;
        }
        this.cep = cep;
        return true;
    }

    @Override
    public String toString() {
        return "rua " + rua + ", numero " + numero + ", bairro " + bairro  + ", cidade " + cidade + "- uf " + uf + ", cep " + cep + "\n";
    }
}
