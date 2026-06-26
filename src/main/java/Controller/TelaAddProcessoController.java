package Controller;

import Dao.ProcessoDAO;
import Model.ProcessoModel;
import View.TelaAddProcessoView;

import javax.swing.*;

public class TelaAddProcessoController {
    private TelaAddProcessoView telaAddProcessoView;
    private ProcessoDAO processoDAO;
    private TelaProcessoController telaProcessoController;

    public TelaAddProcessoController(TelaProcessoController telaProcessoController) {
        telaAddProcessoView = new TelaAddProcessoView();
        processoDAO = new ProcessoDAO();
        this.telaProcessoController = telaProcessoController;

        configurarEventos();
    }

    public void configurarEventos() {
        telaAddProcessoView.getAddProcessoBTN().addActionListener(e -> {
            cadastrarProcesso();
        });
    }

    public void cadastrarProcesso() {
        String numero = telaAddProcessoView.getNumeroInput().getText();
        String descricao = telaAddProcessoView.getDescricaoInput().getText();
        String status = (String) telaAddProcessoView.getStatusCombo().getSelectedItem();
        String idClienteStr = telaAddProcessoView.getIdClienteInput().getText();

        if (numero.isEmpty() || descricao.isEmpty() || idClienteStr.isEmpty()) {
            exibirMensagem("Preencha todos os campos!");
            return;
        }

        if (processoDAO.verificaNumeroRepetido(numero)) {
            exibirMensagem("Número de processo já cadastrado!");
            return;
        }

        int idCliente;
        try {
            idCliente = Integer.parseInt(idClienteStr);
        } catch (NumberFormatException ex) {
            exibirMensagem("ID do cliente inválido!");
            return;
        }

        ProcessoModel processo = new ProcessoModel(numero, descricao, status, idCliente);
        processoDAO.addProcesso(processo);
        telaProcessoController.atualizarTabela();
        exibirMensagem("Processo cadastrado com sucesso!");
    }

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
