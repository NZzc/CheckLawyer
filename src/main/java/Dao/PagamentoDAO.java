package Dao;

import Model.PagamentoModel;

import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {
    private static List<PagamentoModel> listaPagamentos = new ArrayList<>();

    public void addPagamento(PagamentoModel pagamento) {
        listaPagamentos.add(pagamento);
    }

    public void excluirPagamento(int id) {
        listaPagamentos.removeIf(p -> p.getID() == id);
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
}
