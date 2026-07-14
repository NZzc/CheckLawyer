package Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Pagamentos")
public class PagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;                 // Integer, não int: null = ainda não persistido

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(name = "forma_pagamento", nullable = false, length = 30)
    private String formaPagamento;

    @Column(nullable = false, length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteModel cliente;

    @ManyToOne(fetch = FetchType.LAZY)          // opcional → sem nullable=false
    @JoinColumn(name = "processo_id")
    private ProcessoModel processo;

    protected PagamentoModel() {}               // exigido pelo JPA

    public PagamentoModel(String descricao, BigDecimal valor, LocalDate data,
                          String tipo, String formaPagamento, String status,
                          ClienteModel cliente, ProcessoModel processo) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo == null ? null : tipo.toUpperCase();
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.cliente = cliente;
        this.processo = processo;               // pode ser null
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal v) {
        this.valor = v;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate d) {
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
        return cliente.getID();
    }

    public int getIdProcesso() {
        return (processo != null) ? processo.getID() : 0;
    }
}
