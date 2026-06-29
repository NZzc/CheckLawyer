package Model;

import jakarta.persistence.*;

/**
 * @Entity — mapeada para a tabela "processos"
 * @ManyToOne — muitos processos pertencem a um cliente.
 * Gera a coluna "cliente_id" como FK na tabela processos.
 */
@Entity
@Table(name = "processos")
public class ProcessoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String vara;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private String status;

    @Column(name = "data_abertura", nullable = false)
    private String dataAbertura;

    /**
     * Relacionamento ManyToOne com ClienteModel.
     * FetchType.LAZY — o cliente só é carregado do banco quando acessado.
     *
     * @JoinColumn — define o nome da FK na tabela processos.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteModel cliente;

    public ProcessoModel() {
    }

    public ProcessoModel(String numero, String area, String vara, String descricao,
                         String status, String dataAbertura, ClienteModel cliente) {
        this.numero = numero;
        this.area = area;
        this.vara = vara;
        this.descricao = descricao;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.cliente = cliente;
    }

    public int getID() {
        return id;
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

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel c) {
        this.cliente = c;
    }

    public int getIdCliente() {
        return (cliente != null) ? cliente.getID() : 0;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + numero + " (" + area + ")";
    }
}
