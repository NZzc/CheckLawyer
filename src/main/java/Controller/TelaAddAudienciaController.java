package Controller;

import Dao.AudienciaDAO;
import Model.AudienciaModel;
import View.TelaAddAudienciaView;

import javax.swing.*;

public class TelaAddAudienciaController {
    private TelaAddAudienciaView telaAddAudienciaView;
    private AudienciaDAO audienciaDAO;
    private TelaAudienciaController telaAudienciaController;

    public TelaAddAudienciaController(TelaAudienciaController telaAudienciaController) {
        telaAddAudienciaView = new TelaAddAudienciaView();
        audienciaDAO = new AudienciaDAO();
        this.telaAudienciaController = telaAudienciaController;

        configurarEventos();
    }

    public void configurarEventos() {
        telaAddAudienciaView.getAddAudienciaBTN().addActionListener(e -> {
            cadastrarAudiencia();
        });
    }

    public void cadastrarAudiencia() {
        String data = telaAddAudienciaView.getDataInput().getText();
        String hora = telaAddAudienciaView.getHoraInput().getText();
        String local = telaAddAudienciaView.getLocalInput().getText();
        String descricao = telaAddAudienciaView.getDescricaoInput().getText();
        String idProcessoStr = telaAddAudienciaView.getIdProcessoInput().getText();

        if (data.isEmpty() || hora.isEmpty() || local.isEmpty() || descricao.isEmpty() || idProcessoStr.isEmpty()) {
            exibirMensagem("Preencha todos os campos!");
            return;
        }

        int idProcesso;
        try {
            idProcesso = Integer.parseInt(idProcessoStr);
        } catch (NumberFormatException ex) {
            exibirMensagem("ID do processo inválido!");
            return;
        }

        AudienciaModel audiencia = new AudienciaModel(data, hora, local, descricao, idProcesso);
        audienciaDAO.addAudiencia(audiencia);
        telaAudienciaController.atualizarTabela();
        exibirMensagem("Audiência cadastrada com sucesso!");
    }

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
