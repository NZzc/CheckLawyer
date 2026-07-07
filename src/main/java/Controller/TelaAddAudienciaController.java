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
            String data = telaAddAudienciaView.getData();
            String hora = telaAddAudienciaView.getHora();
            String local = telaAddAudienciaView.getLocal();
            String tipo = telaAddAudienciaView.getTipo();
            String descricao = telaAddAudienciaView.getDescricao();
            String resultado = telaAddAudienciaView.getResultado();
            ProcessoModel processoSelecionado = telaAddAudienciaView.getProcessoSelecionado();

            if (data.isEmpty()) throw new CampoVazioException("Data");
            if (hora.isEmpty()) throw new CampoVazioException("Hora");
            if (local.isEmpty()) throw new CampoVazioException("Local");
            if (descricao.isEmpty()) throw new CampoVazioException("Pauta / Descrição");
            if (processoSelecionado == null) throw new CampoVazioException("Processo");

            if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) throw new FormatoInvalidoException("Data", "DD/MM/AAAA");
            int dia = Integer.parseInt(data.split("/")[0]);
            int mes = Integer.parseInt(data.split("/")[1]);
            if (dia < 1 || dia > 31 || mes < 1 || mes > 12)
                throw new FormatoInvalidoException("Data", "data válida DD/MM/AAAA");

            if (!hora.matches("\\d{2}:\\d{2}")) throw new FormatoInvalidoException("Hora", "HH:MM");
            int hh = Integer.parseInt(hora.split(":")[0]);
            int mm = Integer.parseInt(hora.split(":")[1]);
            if (hh > 23 || mm > 59) throw new FormatoInvalidoException("Hora", "entre 00:00 e 23:59");

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
