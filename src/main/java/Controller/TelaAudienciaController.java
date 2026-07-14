package Controller;

import Dao.AudienciaDAO;
import Exception.SelecionarItemException;
import Model.AudienciaModel;
import View.TelaAudienciaView;

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

        telaAudienciaView.getEditarAudienciaBTN().addActionListener(e -> {
            try {
                int ID = telaAudienciaView.getIDlinhaSelecionada();
                AudienciaModel audiencia = audienciaDAO.buscarPorId(ID);

                if (audiencia == null) {
                    exibirErro("Audiência não encontrada.");
                    return;
                }

                new TelaEditAudienciaController(this, audiencia);

            } catch (SelecionarItemException ex) {
                exibirErro(ex.getMessage());
            } catch (Exception ex) {
                exibirErro("Erro inesperado: " + ex.getMessage());
            }
        });

        telaAudienciaView.getExcluirAudienciaBTN().addActionListener(e -> {
            try {
                int ID = telaAudienciaView.getIDlinhaSelecionada();

                if (!telaAudienciaView.confirmarExclusao("Tem certeza que deseja excluir esta audiência?")) return;

                audienciaDAO.excluir(ID);
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
        telaAudienciaView.popularTabela(audienciaDAO.getLista());
    }

    public void exibirErro(String msg) {
        telaAudienciaView.exibirErro(msg);
    }

    public void exibirSucesso(String msg) {
        telaAudienciaView.exibirSucesso(msg);
    }
}
