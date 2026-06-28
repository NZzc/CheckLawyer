package Model;

public class AudienciaModel {
    private final int ID;
    private static int geraID;

    private String data;
    private String hora;
    private String local;
    private String tipo;       // Instrução, Conciliação, Julgamento, etc.
    private String descricao;
    private String resultado;  // preenchido após a audiência ocorrer
    private final int idProcesso;

    public AudienciaModel(String data, String hora, String local, String tipo, String descricao, String resultado, int idProcesso) {
        this.ID = ++geraID;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.tipo = tipo;
        this.descricao = descricao;
        this.resultado = resultado;
        this.idProcesso = idProcesso;
    }

    public int getID() {
        return ID;
    }

    public String getData() {
        return data;
    }

    public void setData(String d) {
        this.data = d;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String h) {
        this.hora = h;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String l) {
        this.local = l;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String t) {
        this.tipo = t;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String d) {
        this.descricao = d;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String r) {
        this.resultado = r;
    }

    public int getIdProcesso() {
        return idProcesso;
    }
}
