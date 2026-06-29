package Model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PF")
public class ClienteFisicoModel extends ClienteModel {

    @Column(unique = true)
    private String cpf;

    public ClienteFisicoModel() {
    }

    public ClienteFisicoModel(String nome, String telefone, String email, String observacao, EnderecoModel endereco, String cpf) {
        super(nome, telefone, email, observacao, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getDocumento() {
        return cpf;
    }
}
