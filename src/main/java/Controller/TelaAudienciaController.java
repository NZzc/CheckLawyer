package Controller;

import Dao.AudienciaDAO;
import Model.AudienciaModel;
//import View.TelaAddAudienciaView;
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

        telaAudienciaView.getAddAudienciaBTN().addActionListener(e -> {
            new TelaAddAudienciaController(this);
        });

        telaAudienciaView.getExcluirAudienciaBTN().addActionListener(e -> {
            int linha = telaAudienciaView.getTabelaAudiencias().getSelectedRow();

            if (linha == -1) {
                exibirMensagem("Selecione uma audiência!");
                return;
            }

            String IDstr = telaAudienciaView.getTabelaAudiencias().getValueAt(linha, 0).toString();
            int ID = Integer.parseInt(IDstr);

            audienciaDAO.excluirAudiencia(ID);
            atualizarTabela();
        });
    }

    public void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) telaAudienciaView.getTabelaAudiencias().getModel();
        model.setRowCount(0);

        for (AudienciaModel audiencia : audienciaDAO.getListaAudiencias()) {
            model.addRow(new Object[]{
                    audiencia.getID(),
                    audiencia.getData(),
                    audiencia.getHora(),
                    audiencia.getLocal(),
                    audiencia.getDescricao(),
                    audiencia.getIdProcesso()});
        }
    }

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
