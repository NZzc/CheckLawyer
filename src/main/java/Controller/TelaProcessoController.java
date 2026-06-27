package Controller;

import Dao.ProcessoDAO;
import Exception.SelecionarItemException;
import Model.ProcessoModel;
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

        telaProcessosView.getAddProcessoBTN().addActionListener(e -> new TelaAddProcessoController(this));

        telaProcessosView.getExcluirProcessoBTN().addActionListener(e -> {
            try {
                int linha = telaProcessosView.getTabelaProcessos().getSelectedRow();
                if (linha == -1) throw new SelecionarItemException("processo");

                int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este processo?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
                if (confirmacao != JOptionPane.YES_OPTION) return;

                int ID = Integer.parseInt(telaProcessosView.getTabelaProcessos().getValueAt(linha, 0).toString());
                processoDAO.excluirProcesso(ID);
                atualizarTabela();
                exibirSucesso("Processo excluído com sucesso!");

            } catch (SelecionarItemException ex) {
                exibirErro(ex.getMessage());
            } catch (Exception ex) {
                exibirErro("Erro inesperado: " + ex.getMessage());
            }
        });
    }

    public void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) telaProcessosView.getTabelaProcessos().getModel();
        model.setRowCount(0);
        for (ProcessoModel p : processoDAO.getListaProcessos()) {
            model.addRow(new Object[]{p.getID(), p.getNumero(), p.getDescricao(), p.getStatus(), p.getIdCliente()});
        }
    }

    public void exibirErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
