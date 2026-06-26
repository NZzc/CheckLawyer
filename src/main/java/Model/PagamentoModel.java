package Model;

public class PagamentoModel {
    private final int ID;
    private static int geraID;

    private String descricao;
    private double valor;
    private String data;
    private String tipo;  // "RECEITA" ou "DESPESA"
    private final int idCliente;

    public PagamentoModel(String descricao, double valor, String data, String tipo, int idCliente) {
        this.ID = ++geraID;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo.toUpperCase();
        this.idCliente = idCliente;
    }

    public int getID() {
        return ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.toUpperCase();
    }

    public int getIdCliente() {
        return idCliente;
    }
}
