package Dao;

import Model.PagamentoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PagamentoDAO {
    private static List<PagamentoModel> listaPagamentos = new ArrayList<>();

    public void inserir(PagamentoModel pagamento) {
        listaPagamentos.add(pagamento);
    }

    public void excluir(int id) {
        listaPagamentos.removeIf(pagamento -> pagamento.getID() == id);
    }

    public List<PagamentoModel> getListaPagamentos() {
        return listaPagamentos;
    }

    public double calcularSaldo() {
        double saldo = 0;
        for (PagamentoModel p : listaPagamentos) {
            if (p.getTipo().equals("RECEITA")) {
                saldo += p.getValor();
            } else {
                saldo -= p.getValor();
            }
        }
        return saldo;
    }

    // Usa um Map para somar os valores agrupados por tipo
    public Map<String, Double> getTotalPorTipo() {
        Map<String, Double> totalPorTipo = new HashMap<>();
        for (PagamentoModel p : listaPagamentos) {
            totalPorTipo.merge(p.getTipo(), p.getValor(), Double::sum);
        }
        return totalPorTipo;
    }

    // Usa um Set para obter as formas de pagamento utilizadas, sem repetição e em ordem alfabética
    public Set<String> getFormasPagamentoUtilizadas() {
        Set<String> formas = new TreeSet<>();
        for (PagamentoModel p : listaPagamentos) {
            formas.add(p.getFormaPagamento());
        }
        return formas;
    }
}
