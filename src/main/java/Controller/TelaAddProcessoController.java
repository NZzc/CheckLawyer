package Controller;

import Dao.ProcessoDAO;
import Exception.*;
import Model.ProcessoModel;
import View.TelaAddProcessoView;

import javax.swing.*;

public class TelaAddProcessoController {
    private TelaAddProcessoView telaAddProcessoView;
    private ProcessoDAO processoDAO;
    private TelaProcessoController telaProcessoController;

    public TelaAddProcessoController(TelaProcessoController telaProcessoController) {
        telaAddProcessoView = new TelaAddProcessoView();
        processoDAO = new ProcessoDAO();
        this.telaProcessoController = telaProcessoController;
        configurarEventos();
    }

    public void configurarEventos() {
        telaAddProcessoView.getAddProcessoBTN().addActionListener(e -> cadastrarProcesso());
    }

    public void cadastrarProcesso() {
        try {
            String numero = telaAddProcessoView.getNumeroInput().getText().trim();
            String descricao = telaAddProcessoView.getDescricaoInput().getText().trim();
            String status = (String) telaAddProcessoView.getStatusCombo().getSelectedItem();
            String idClienteStr = telaAddProcessoView.getIdClienteInput().getText().trim();

            // Campos obrigatórios
            if (numero.isEmpty()) throw new CampoVazioException("Número do Processo");
            if (descricao.isEmpty()) throw new CampoVazioException("Descrição");
            if (idClienteStr.isEmpty()) throw new CampoVazioException("ID do Cliente");

            // Formato do número do processo (ex: 0000000-00.0000.0.00.0000)
            if (!numero.matches("\\d{7}-\\d{2}\\.\\d{4}\\.\\d\\.\\d{2}\\.\\d{4}") && numero.length() < 5) {
                throw new FormatoInvalidoException("Número do Processo", "mínimo 5 caracteres");
            }

            // ID do cliente deve ser numérico e positivo
            int idCliente;
            try {
                idCliente = Integer.parseInt(idClienteStr);
            } catch (NumberFormatException ex) {
                throw new FormatoInvalidoException("ID do Cliente", "número inteiro positivo");
            }
            if (idCliente <= 0) throw new ValorNegativoException("ID do Cliente");

            // Número de processo duplicado
            if (processoDAO.verificaNumeroRepetido(numero)) {
                throw new RegistroDuplicadoException("número", numero);
            }

            ProcessoModel processo = new ProcessoModel(numero, descricao, status, idCliente);
            processoDAO.addProcesso(processo);
            telaProcessoController.atualizarTabela();
            exibirSucesso("Processo \"" + numero + "\" cadastrado com sucesso!");

        } catch (CampoVazioException | FormatoInvalidoException | ValorNegativoException |
                 RegistroDuplicadoException ex) {
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
