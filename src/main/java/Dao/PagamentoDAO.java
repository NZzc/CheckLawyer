package Dao;

import Model.PagamentoModel;
import Util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PagamentoDAO {

    public void inserir(PagamentoModel pagamento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pagamento);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            PagamentoModel pagamento = em.find(PagamentoModel.class, id);
            if (pagamento != null) em.remove(pagamento);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<PagamentoModel> getListaPagamentos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM PagamentoModel p "
                            + "JOIN FETCH p.cliente "
                            + "LEFT JOIN FETCH p.processo "
                            + "ORDER BY p.data DESC, p.ID DESC",
                    PagamentoModel.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public BigDecimal calcularSaldo() {
        BigDecimal saldo = BigDecimal.ZERO;
        for (PagamentoModel p : getListaPagamentos()) {
            if (p.getTipo().equals("RECEITA")) {
                saldo = saldo.add(p.getValor());
            } else {
                saldo = saldo.subtract(p.getValor());
            }
        }
        return saldo;
    }


    // Usa um Map para somar os valores agrupados por tipo
    public Map<String, BigDecimal> getTotalPorTipo() {
        Map<String, BigDecimal> totalPorTipo = new HashMap<>();
        for (PagamentoModel p : getListaPagamentos()) {
            totalPorTipo.merge(p.getTipo(), p.getValor(), BigDecimal::add);
        }
        return totalPorTipo;
    }


    // Usa um Set para obter as formas de pagamento utilizadas, sem repetição e em ordem alfabética
    public Set<String> getFormasPagamentoUtilizadas() {
        Set<String> formas = new TreeSet<>();
        for (PagamentoModel p : getListaPagamentos()) {
            formas.add(p.getFormaPagamento());
        }
        return formas;
    }
}
