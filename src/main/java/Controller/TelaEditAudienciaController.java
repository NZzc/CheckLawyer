package Controller;

import Dao.AudienciaDAO;
import Dao.ProcessoDAO;
import Exception.CampoVazioException;
import Exception.FormatoInvalidoException;
import Model.AudienciaModel;
import Model.ProcessoModel;
import View.TelaAddAudienciaView;

public class TelaEditAudienciaController {

    private TelaAddAudienciaView telaEditAudienciaView;
    private AudienciaDAO audienciaDAO;
    private ProcessoDAO processoDAO;
    private AudienciaModel audiencia;
    private TelaAudienciaController telaAudienciaController;

    public TelaEditAudienciaController(TelaAudienciaController telaAudienciaController, AudienciaModel audiencia) {
        this.telaAudienciaController = telaAudienciaController;
        this.audiencia = audiencia;
        this.audienciaDAO = new AudienciaDAO();
        this.processoDAO = new ProcessoDAO();
        this.telaEditAudienciaView = new TelaAddAudienciaView();

        telaEditAudienciaView.popularProcessos(processoDAO.getLista());
        telaEditAudienciaView.configurarModoEdicao();
        telaEditAudienciaView.preencherAudiencia(audiencia);
        configurarEventos();
    }

    private void configurarEventos() {
        telaEditAudienciaView.getAddAudienciaBTN().addActionListener(e -> editarAudiencia());
    }

    private void editarAudiencia() {
        try {
            audiencia.setData(telaEditAudienciaView.getData());
            audiencia.setHora(telaEditAudienciaView.getHora());
            audiencia.setLocal(telaEditAudienciaView.getLocal());
            audiencia.setTipo(telaEditAudienciaView.getTipo());
            audiencia.setDescricao(telaEditAudienciaView.getDescricao());
            audiencia.setResultado(telaEditAudienciaView.getResultado());

            ProcessoModel processoSelecionado = telaEditAudienciaView.getProcessoSelecionado();
            audiencia.setProcesso(processoSelecionado);

            audienciaDAO.editar(audiencia);
            telaAudienciaController.atualizarTabela();
            telaEditAudienciaView.exibirSucesso("Audiência atualizada com sucesso!");
            telaEditAudienciaView.dispose();

        } catch (CampoVazioException | FormatoInvalidoException ex) {
            telaEditAudienciaView.exibirErro(ex.getMessage());
        } catch (Exception ex) {
            telaEditAudienciaView.exibirErro("Erro inesperado: " + ex.getMessage());
        }
    }
}
