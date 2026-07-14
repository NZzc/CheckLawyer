package Controller;

import Dao.PagamentoDAO;
import Exception.SelecionarItemException;
import View.TelaFinanceiroView;

import java.math.BigDecimal;
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

        BigDecimal saldo = pagamentoDAO.calcularSaldo();

        // Map: total de receitas e despesas agrupados por tipo
        Map<String, BigDecimal> totalPorTipo = pagamentoDAO.getTotalPorTipo();
        BigDecimal totalReceitas = totalPorTipo.getOrDefault("RECEITA", BigDecimal.valueOf(0.0));
        BigDecimal totalDespesas = totalPorTipo.getOrDefault("DESPESA", BigDecimal.valueOf(0.0));

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
