package Controller;

import Dao.AudienciaDAO;
import Exception.*;
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
        telaAddAudienciaView.getAddAudienciaBTN().addActionListener(e -> cadastrarAudiencia());
    }

    public void cadastrarAudiencia() {
        try {
            String data          = telaAddAudienciaView.getDataInput().getText().trim();
            String hora          = telaAddAudienciaView.getHoraInput().getText().trim();
            String local         = telaAddAudienciaView.getLocalInput().getText().trim();
            String descricao     = telaAddAudienciaView.getDescricaoInput().getText().trim();
            String idProcessoStr = telaAddAudienciaView.getIdProcessoInput().getText().trim();

            // Campos obrigatórios
            if (data.isEmpty())          throw new CampoVazioException("Data");
            if (hora.isEmpty())          throw new CampoVazioException("Hora");
            if (local.isEmpty())         throw new CampoVazioException("Local");
            if (descricao.isEmpty())     throw new CampoVazioException("Descrição");
            if (idProcessoStr.isEmpty()) throw new CampoVazioException("ID do Processo");

            // Formato de data DD/MM/AAAA
            if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
                throw new FormatoInvalidoException("Data", "DD/MM/AAAA");
            }

            // Validação de data real (dia e mês dentro dos limites)
            String[] partesData = data.split("/");
            int dia = Integer.parseInt(partesData[0]);
            int mes = Integer.parseInt(partesData[1]);
            int ano = Integer.parseInt(partesData[2]);
            if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 1900) {
                throw new FormatoInvalidoException("Data", "data válida no formato DD/MM/AAAA");
            }

            // Formato de hora HH:MM
            if (!hora.matches("\\d{2}:\\d{2}")) {
                throw new FormatoInvalidoException("Hora", "HH:MM");
            }
            int hh = Integer.parseInt(hora.split(":")[0]);
            int mm = Integer.parseInt(hora.split(":")[1]);
            if (hh > 23 || mm > 59) {
                throw new FormatoInvalidoException("Hora", "hora entre 00:00 e 23:59");
            }

            // ID do processo deve ser numérico e positivo
            int idProcesso;
            try {
                idProcesso = Integer.parseInt(idProcessoStr);
            } catch (NumberFormatException ex) {
                throw new FormatoInvalidoException("ID do Processo", "número inteiro positivo");
            }
            if (idProcesso <= 0) throw new ValorNegativoException("ID do Processo");

            AudienciaModel audiencia = new AudienciaModel(data, hora, local, descricao, idProcesso);
            audienciaDAO.addAudiencia(audiencia);
            telaAudienciaController.atualizarTabela();
            exibirSucesso("Audiência cadastrada com sucesso!");

        } catch (CampoVazioException | FormatoInvalidoException | ValorNegativoException ex) {
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
