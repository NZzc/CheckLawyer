package Model;

public class PagamentoModel {
    private final int ID;
    private static int geraID;

    private String descricao;
    private double valor;
    private String data;
    private String tipo;          // "RECEITA" ou "DESPESA"
    private String formaPagamento; // PIX, Boleto, Dinheiro, Transferência, Cartão
    private String status;        // "PAGO", "PENDENTE", "ATRASADO"
    private final int idCliente;
    private final int idProcesso;

    public PagamentoModel(String descricao, double valor, String data, String tipo, String formaPagamento, String status, int idCliente, int idProcesso) {
        this.ID = ++geraID;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo.toUpperCase();
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.idCliente = idCliente;
        this.idProcesso = idProcesso;
    }

    public int getID() {
        return ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String d) {
        this.descricao = d;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double v) {
        this.valor = v;
    }

    public String getData() {
        return data;
    }

    public void setData(String d) {
        this.data = d;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String t) {
        this.tipo = t.toUpperCase();
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String f) {
        this.formaPagamento = f;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String s) {
        this.status = s;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdProcesso() {
        return idProcesso;
    }
}
