package Model;

public class ProcessoModel {
    private final int ID;
    private static int geraID;

    private String numero;
    private String descricao;
    private String status;
    private final int idCliente;

    public ProcessoModel(String numero, String descricao, String status, int idCliente) {
        this.ID = ++geraID;
        this.numero = numero;
        this.descricao = descricao;
        this.status = status;
        this.idCliente = idCliente;
    }

    public int getID() {
        return ID;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdCliente() {
        return idCliente;
    }
}
