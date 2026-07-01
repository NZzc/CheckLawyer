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
        BtnAddProcesso();
    }

    private void BtnAddProcesso() {
        telaProcessosView.getAddProcessoBTN().addActionListener(e -> new TelaAddProcessoController(this));

        telaProcessosView.getExcluirProcessoBTN().addActionListener(e -> {
            try {
                int linha = telaProcessosView.getTabelaProcessos().getSelectedRow();
                if (linha == -1) throw new SelecionarItemException("processo");

                int ok = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este processo?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
                if (ok != JOptionPane.YES_OPTION) return;

                int ID = Integer.parseInt(telaProcessosView.getTabelaProcessos().getValueAt(linha, 0).toString());
                processoDAO.excluir(ID);
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
        for (ProcessoModel p : processoDAO.getLista()) {
            model.addRow(new Object[]{p.getID(), p.getNumero(), p.getArea(), p.getVara(), p.getDescricao(), p.getStatus(), p.getDataAbertura(), p.getIdCliente()});
        }
    }

    public ProcessoDAO getProcessoDAO() {
        return processoDAO;
    }

    public void exibirErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
