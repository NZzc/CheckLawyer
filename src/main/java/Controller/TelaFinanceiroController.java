package Controller;

import Dao.PagamentoDAO;
import Exception.SelecionarItemException;
import Model.PagamentoModel;
import View.TelaFinanceiroView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
                int linha = telaFinanceiroView.getTabelaPagamentos().getSelectedRow();
                if (linha == -1) throw new SelecionarItemException("pagamento");

                int ok = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este pagamento?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
                if (ok != JOptionPane.YES_OPTION) return;

                int ID = Integer.parseInt(telaFinanceiroView.getTabelaPagamentos().getValueAt(linha, 0).toString());
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
        DefaultTableModel model = (DefaultTableModel) telaFinanceiroView.getTabelaPagamentos().getModel();
        model.setRowCount(0);
        for (PagamentoModel p : pagamentoDAO.getListaPagamentos()) {
            model.addRow(new Object[]{p.getID(), p.getDescricao(), String.format("%.2f", p.getValor()), p.getData(), p.getTipo(), p.getFormaPagamento(), p.getStatus(), p.getIdCliente(), p.getIdProcesso() == 0 ? "-" : p.getIdProcesso()});
        }

        double saldo = pagamentoDAO.calcularSaldo();

        // Map: total de receitas e despesas agrupados por tipo
        Map<String, Double> totalPorTipo = pagamentoDAO.getTotalPorTipo();
        double totalReceitas = totalPorTipo.getOrDefault("RECEITA", 0.0);
        double totalDespesas = totalPorTipo.getOrDefault("DESPESA", 0.0);

        // Set: formas de pagamento distintas já utilizadas
        Set<String> formasUtilizadas = pagamentoDAO.getFormasPagamentoUtilizadas();

        String texto = String.format("Saldo: R$ %.2f | Receitas: R$ %.2f | Despesas: R$ %.2f | Formas de pagamento: %s", saldo, totalReceitas, totalDespesas, String.join(", ", formasUtilizadas));
        telaFinanceiroView.getSaldoLabel().setText(texto);
    }

    public void exibirErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
