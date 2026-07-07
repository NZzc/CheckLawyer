package Controller;

import Dao.PagamentoDAO;
import Exception.SelecionarItemException;
import View.TelaFinanceiroView;

import java.util.Map;
import java.util.Set;

public class TelaFinanceiroController {
    private PagamentoDAO pagamentoDAO;
    private TelaFinanceiroView telaFinanceiroView;

    public TelaFinanceiroController() {
        telaFinanceiroView = new TelaFinanceiroView();
        pagamentoDAO = new PagamentoDAO();
        atualizarTabela();
        BtnAddPagamento();
    }

    private void BtnAddPagamento() {
        telaFinanceiroView.getAddPagamentoBTN().addActionListener(e -> new TelaAddPagamentoController(this));

        telaFinanceiroView.getExcluirPagamentoBTN().addActionListener(e -> {
            try {
                int ID = telaFinanceiroView.getIDlinhaSelecionada();

                if (!telaFinanceiroView.confirmarExclusao("Tem certeza que deseja excluir este pagamento?")) return;

                pagamentoDAO.excluir(ID);
                atualizarTabela();
                exibirSucesso("Pagamento excluído com sucesso!");

            } catch (SelecionarItemException ex) {
                exibirErro(ex.getMessage());
            } catch (Exception ex) {
                exibirErro("Erro inesperado: " + ex.getMessage());
            }
        });
    }

    public void atualizarTabela() {
        telaFinanceiroView.popularTabela(pagamentoDAO.getListaPagamentos());

        double saldo = pagamentoDAO.calcularSaldo();

        // Map: total de receitas e despesas agrupados por tipo
        Map<String, Double> totalPorTipo = pagamentoDAO.getTotalPorTipo();
        double totalReceitas = totalPorTipo.getOrDefault("RECEITA", 0.0);
        double totalDespesas = totalPorTipo.getOrDefault("DESPESA", 0.0);

        // Set: formas de pagamento distintas já utilizadas
        Set<String> formasUtilizadas = pagamentoDAO.getFormasPagamentoUtilizadas();

        telaFinanceiroView.atualizarResumo(saldo, totalReceitas, totalDespesas, formasUtilizadas);
    }

    public void exibirErro(String msg) {
        telaFinanceiroView.exibirErro(msg);
    }

    public void exibirSucesso(String msg) {
        telaFinanceiroView.exibirSucesso(msg);
    }
}
