package Model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PJ")
public class ClienteJuridicoModel extends ClienteModel {

    @Column(unique = true)
    private String cnpj;

    public ClienteJuridicoModel() {
    }

    public ClienteJuridicoModel(String nome, String telefone, String email, String observacao, EnderecoModel endereco, String cnpj) {
        super(nome, telefone, email, observacao, endereco);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getDocumento() {
        return cnpj;
    }
}
