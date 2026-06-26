package Controller;

import Dao.PagamentoDAO;
import Model.PagamentoModel;
import View.TelaFinanceiroView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaFinanceiroController {
    private PagamentoDAO pagamentoDAO;
    private TelaFinanceiroView telaFinanceiroView;

    public TelaFinanceiroController() {
        telaFinanceiroView = new TelaFinanceiroView();
        pagamentoDAO = new PagamentoDAO();

        atualizarTabela();
        configurarEventos();
    }

    private void configurarEventos() {

        telaFinanceiroView.getAddPagamentoBTN().addActionListener(e -> {
            new TelaAddPagamentoController(this);
        });

        telaFinanceiroView.getExcluirPagamentoBTN().addActionListener(e -> {
            int linha = telaFinanceiroView.getTabelaPagamentos().getSelectedRow();

            if (linha == -1) {
                exibirMensagem("Selecione um pagamento!");
                return;
            }

            String IDstr = telaFinanceiroView.getTabelaPagamentos().getValueAt(linha, 0).toString();
            int ID = Integer.parseInt(IDstr);

            pagamentoDAO.excluirPagamento(ID);
            atualizarTabela();
        });
    }

    public void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) telaFinanceiroView.getTabelaPagamentos().getModel();
        model.setRowCount(0);

        for (PagamentoModel p : pagamentoDAO.getListaPagamentos()) {
            model.addRow(new Object[]{p.getID(), p.getDescricao(), String.format("%.2f", p.getValor()), p.getData(), p.getTipo(), p.getIdCliente()});
        }

        double saldo = pagamentoDAO.calcularSaldo();
        telaFinanceiroView.getSaldoLabel().setText(String.format("Saldo: R$ %.2f", saldo));
    }

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
