package Controller;

import Dao.ProcessoDAO;
import Model.ProcessoModel;
import View.TelaAddProcessoView;
import View.TelaProcessosView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaProcessoController {
    private ProcessoDAO processoDAO;
    private TelaProcessosView telaProcessosView;

    public TelaProcessoController() {
        telaProcessosView = new TelaProcessosView();
        processoDAO = new ProcessoDAO();

        atualizarTabela();
        configurarEventos();
    }

    private void configurarEventos() {

        telaProcessosView.getAddProcessoBTN().addActionListener(e -> {
            new TelaAddProcessoController(this);
        });

        telaProcessosView.getExcluirProcessoBTN().addActionListener(e -> {
            int linha = telaProcessosView.getTabelaProcessos().getSelectedRow();

            if (linha == -1) {
                exibirMensagem("Selecione um processo!");
                return;
            }

            String IDstr = telaProcessosView.getTabelaProcessos().getValueAt(linha, 0).toString();
            int ID = Integer.parseInt(IDstr);

            processoDAO.excluirProcesso(ID);
            atualizarTabela();
        });
    }

    public void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) telaProcessosView.getTabelaProcessos().getModel();
        model.setRowCount(0);

        for (ProcessoModel processo : processoDAO.getListaProcessos()) {
            model.addRow(new Object[]{processo.getID(), processo.getNumero(), processo.getDescricao(), processo.getStatus(), processo.getIdCliente()});
        }
    }

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
