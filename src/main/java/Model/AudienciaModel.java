package Model;

public class AudienciaModel {
    private final int ID;
    private static int geraID;

    private String data;
    private String hora;
    private String local;
    private String descricao;
    private final int idProcesso;

    public AudienciaModel(String data, String hora, String local, String descricao, int idProcesso) {
        this.ID = ++geraID;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.descricao = descricao;
        this.idProcesso = idProcesso;
    }

    public int getID() {
        return ID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdProcesso() {
        return idProcesso;
    }
}
