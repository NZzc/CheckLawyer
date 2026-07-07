package Controller;

import Dao.AudienciaDAO;
import Dao.ProcessoDAO;
import Exception.*;
import Model.AudienciaModel;
import Model.ProcessoModel;
import View.TelaAddAudienciaView;

public class TelaAddAudienciaController {
    private TelaAddAudienciaView telaAddAudienciaView;
    private AudienciaDAO audienciaDAO;
    private ProcessoDAO processoDAO;
    private TelaAudienciaController telaAudienciaController;

    public TelaAddAudienciaController(TelaAudienciaController telaAudienciaController) {
        telaAddAudienciaView = new TelaAddAudienciaView();
        audienciaDAO = new AudienciaDAO();
        processoDAO = new ProcessoDAO();
        this.telaAudienciaController = telaAudienciaController;

        ComboProcessos();
        configurarEventos();
    }

    private void ComboProcessos() {
        telaAddAudienciaView.popularProcessos(processoDAO.getLista());
    }

    public void configurarEventos() {
        telaAddAudienciaView.getAddAudienciaBTN().addActionListener(e -> cadastrarAudiencia());
    }

    public void cadastrarAudiencia() {
        try {
            // A View lê e valida cada campo, lançando a exceção correspondente
            String data = telaAddAudienciaView.getData();
            String hora = telaAddAudienciaView.getHora();
            String local = telaAddAudienciaView.getLocal();
            String tipo = telaAddAudienciaView.getTipo();
            String descricao = telaAddAudienciaView.getDescricao();
            String resultado = telaAddAudienciaView.getResultado();
            ProcessoModel processoSelecionado = telaAddAudienciaView.getProcessoSelecionado();

            AudienciaModel audiencia = new AudienciaModel(data, hora, local, tipo, descricao, resultado, processoSelecionado.getID());
            audienciaDAO.inserir(audiencia);
            telaAudienciaController.atualizarTabela();
            exibirSucesso("Audiência cadastrada com sucesso!");

        } catch (CampoVazioException | FormatoInvalidoException ex) {
            exibirErro(ex.getMessage());
        } catch (Exception ex) {
            exibirErro("Erro inesperado: " + ex.getMessage());
        }
    }

    public void exibirErro(String msg) {
        telaAddAudienciaView.exibirErro(msg);
    }

    public void exibirSucesso(String msg) {
        telaAddAudienciaView.exibirSucesso(msg);
    }
}
