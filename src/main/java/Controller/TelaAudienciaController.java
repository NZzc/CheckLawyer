package Controller;

import Dao.AudienciaDAO;
import Exception.SelecionarItemException;
import Model.AudienciaModel;
import View.TelaAudienciaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaAudienciaController {
    private AudienciaDAO audienciaDAO;
    private TelaAudienciaView telaAudienciaView;

    public TelaAudienciaController() {
        telaAudienciaView = new TelaAudienciaView();
        audienciaDAO = new AudienciaDAO();
        atualizarTabela();
        configurarEventos();
    }

    private void configurarEventos() {

        telaAudienciaView.getAddAudienciaBTN().addActionListener(e -> new TelaAddAudienciaController(this));

        telaAudienciaView.getExcluirAudienciaBTN().addActionListener(e -> {
            try {
                int linha = telaAudienciaView.getTabelaAudiencias().getSelectedRow();
                if (linha == -1) throw new SelecionarItemException("audiência");

                int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta audiência?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
                if (confirmacao != JOptionPane.YES_OPTION) return;

                int ID = Integer.parseInt(telaAudienciaView.getTabelaAudiencias().getValueAt(linha, 0).toString());
                audienciaDAO.excluirAudiencia(ID);
                atualizarTabela();
                exibirSucesso("Audiência excluída com sucesso!");

            } catch (SelecionarItemException ex) {
                exibirErro(ex.getMessage());
            } catch (Exception ex) {
                exibirErro("Erro inesperado: " + ex.getMessage());
            }
        });
    }

    public void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) telaAudienciaView.getTabelaAudiencias().getModel();
        model.setRowCount(0);
        for (AudienciaModel a : audienciaDAO.getListaAudiencias()) {
            model.addRow(new Object[]{a.getID(), a.getData(), a.getHora(), a.getLocal(), a.getDescricao(), a.getIdProcesso()});
        }
    }

    public void exibirErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
