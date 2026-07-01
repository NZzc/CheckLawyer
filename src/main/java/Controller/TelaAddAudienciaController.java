package Controller;

import Dao.AudienciaDAO;
import Dao.ProcessoDAO;
import Exception.*;
import Model.AudienciaModel;
import Model.ProcessoModel;
import View.TelaAddAudienciaView;

import javax.swing.*;

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
        BtnAddAudiencia();
    }

    private void ComboProcessos() {
        JComboBox<ProcessoModel> combo = telaAddAudienciaView.getProcessoCombo();
        combo.removeAllItems();
        for (ProcessoModel p : processoDAO.getLista()) {
            combo.addItem(p);
        }
        if (combo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum processo cadastrado.\nCadastre um processo antes de adicionar uma audiência.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void BtnAddAudiencia() {
        telaAddAudienciaView.getAddAudienciaBTN().addActionListener(e -> cadastrarAudiencia());
    }

    public void cadastrarAudiencia() {
        try {
            String data = telaAddAudienciaView.getDataInput().getText().trim();
            String hora = telaAddAudienciaView.getHoraInput().getText().trim();
            String local = telaAddAudienciaView.getLocalInput().getText().trim();
            String tipo = (String) telaAddAudienciaView.getTipoCombo().getSelectedItem();
            String descricao = telaAddAudienciaView.getDescricaoInput().getText().trim();
            String resultado = telaAddAudienciaView.getResultadoInput().getText().trim();
            ProcessoModel processoSelecionado = (ProcessoModel) telaAddAudienciaView.getProcessoCombo().getSelectedItem();

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
        JOptionPane.showMessageDialog(null, msg, "Erro de validação", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
