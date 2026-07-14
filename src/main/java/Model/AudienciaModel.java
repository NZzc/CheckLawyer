package Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Audiencias")
public class AudienciaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String hora;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private String tipo;       // Instrução, Conciliação, Julgamento, etc.

    @Column(nullable = false)
    private String descricao;

    @Column()
    private String resultado;  // preenchido após a audiência ocorrer

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Processo_id", nullable = false)
    private ProcessoModel processo;


    public AudienciaModel() {}

    public AudienciaModel(String data, String hora, String local, String tipo, String descricao, String resultado, ProcessoModel processo) {
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.tipo = tipo;
        this.descricao = descricao;
        this.resultado = resultado;
        this.processo = processo;
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
        return (processo != null) ? processo.getID() : 0;
    }

    public ProcessoModel getProcesso() {
        return processo;
    }

    public void setProcesso(ProcessoModel processo) {
        this.processo = processo;
    }
}
