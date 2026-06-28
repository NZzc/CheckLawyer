package Model;

public class ProcessoModel {
    private final int ID;
    private static int geraID;

    private String numero;
    private String area;       // Cível, Trabalhista, Criminal, Família, etc.
    private String vara;       // ex: "2ª Vara Cível de SP"
    private String descricao;
    private String status;
    private String dataAbertura;
    private final int idCliente;

    public ProcessoModel(String numero, String area, String vara, String descricao, String status, String dataAbertura, int idCliente) {
        this.ID = ++geraID;
        this.numero = numero;
        this.area = area;
        this.vara = vara;
        this.descricao = descricao;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.idCliente = idCliente;
    }

    public int getID() {
        return ID;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String n) {
        this.numero = n;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String a) {
        this.area = a;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String v) {
        this.vara = v;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String d) {
        this.descricao = d;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String s) {
        this.status = s;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String d) {
        this.dataAbertura = d;
    }

    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Usado para popular JComboBox
     */
    @Override
    public String toString() {
        return "[" + ID + "] " + numero + " (" + area + ")";
    }
}
