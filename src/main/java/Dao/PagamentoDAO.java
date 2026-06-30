package Dao;

import Model.PagamentoModel;

import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO implements PersistivelInterface <PagamentoModel> {
    private static List<PagamentoModel> listaPagamentos = new ArrayList<>();

    public void inserir(PagamentoModel pagamento) {
        listaPagamentos.add(pagamento);
    }

    public void excluir(int id) {
        listaPagamentos.removeIf(pagamento -> pagamento.getID() == id);
    }

    public List<PagamentoModel> getLista() {
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
