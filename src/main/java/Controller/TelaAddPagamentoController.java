package Controller;

import Dao.PagamentoDAO;
import Model.PagamentoModel;
import View.TelaAddPagamentoView;

import javax.swing.*;

public class TelaAddPagamentoController {
    private TelaAddPagamentoView telaAddPagamentoView;
    private PagamentoDAO pagamentoDAO;
    private TelaFinanceiroController telaFinanceiroController;

    public TelaAddPagamentoController(TelaFinanceiroController telaFinanceiroController) {
        telaAddPagamentoView = new TelaAddPagamentoView();
        pagamentoDAO = new PagamentoDAO();
        this.telaFinanceiroController = telaFinanceiroController;

        configurarEventos();
    }

    public void configurarEventos() {
        telaAddPagamentoView.getAddPagamentoBTN().addActionListener(e -> {
            cadastrarPagamento();
        });
    }

    public void cadastrarPagamento() {
        String descricao = telaAddPagamentoView.getDescricaoInput().getText();
        String valorStr = telaAddPagamentoView.getValorInput().getText();
        String data = telaAddPagamentoView.getDataInput().getText();
        String tipo = (String) telaAddPagamentoView.getTipoCombo().getSelectedItem();
        String idClienteStr = telaAddPagamentoView.getIdClienteInput().getText();

        if (descricao.isEmpty() || valorStr.isEmpty() || data.isEmpty() || idClienteStr.isEmpty()) {
            exibirMensagem("Preencha todos os campos!");
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(valorStr.replace(",", "."));
        } catch (NumberFormatException ex) {
            exibirMensagem("Valor inválido! Use números (ex: 150.00)");
            return;
        }

        int idCliente;
        try {
            idCliente = Integer.parseInt(idClienteStr);
        } catch (NumberFormatException ex) {
            exibirMensagem("ID do cliente inválido!");
            return;
        }

        PagamentoModel pagamento = new PagamentoModel(descricao, valor, data, tipo, idCliente);
        pagamentoDAO.addPagamento(pagamento);
        telaFinanceiroController.atualizarTabela();
        exibirMensagem("Pagamento cadastrado com sucesso!");
    }

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
