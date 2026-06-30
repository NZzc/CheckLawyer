package Model;

import jakarta.persistence.Embeddable;

/**
 * @Embeddable — não tem tabela própria.
 * As colunas são embutidas diretamente na tabela "clientes".
 */
@Embeddable
public class EnderecoModel {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public EnderecoModel() {
    }

    public EnderecoModel(String rua, String numero, String bairro, String cidade, String uf, String cep) {
        this.rua = rua.toUpperCase();
        this.numero = numero.toUpperCase();
        this.bairro = bairro.toUpperCase();
        this.cidade = cidade.toUpperCase();
        this.uf = uf.toUpperCase();
        this.cep = cep;
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

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "rua " + rua + ", numero " + numero + ", bairro " + bairro + ", cidade " + cidade + "- uf " + uf + ", cep " + cep + "\n";
    }
}
