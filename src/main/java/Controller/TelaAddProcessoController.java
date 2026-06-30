package Controller;

import Dao.ClienteDAO;
import Dao.ProcessoDAO;
import Exception.*;
import Model.ClienteModel;
import Model.ProcessoModel;
import View.TelaAddProcessoView;

import javax.swing.*;

public class TelaAddProcessoController {
    private TelaAddProcessoView telaAddProcessoView;
    private ProcessoDAO processoDAO;
    private ClienteDAO clienteDAO;
    private TelaProcessoController telaProcessoController;

    public TelaAddProcessoController(TelaProcessoController telaProcessoController) {
        telaAddProcessoView = new TelaAddProcessoView();
        processoDAO = new ProcessoDAO();
        clienteDAO = new ClienteDAO();
        this.telaProcessoController = telaProcessoController;

        popularComboClientes();
        configurarEventos();
    }

    private void popularComboClientes() {
        JComboBox<ClienteModel> combo = telaAddProcessoView.getClienteCombo();
        combo.removeAllItems();
        for (ClienteModel c : clienteDAO.getLista()) {
            combo.addItem(c);
        }
        if (combo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.\nCadastre um cliente antes de adicionar um processo.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void configurarEventos() {
        telaAddProcessoView.getAddProcessoBTN().addActionListener(e -> cadastrarProcesso());
    }

    public void cadastrarProcesso() {
        try {
            String numero = telaAddProcessoView.getNumeroInput().getText().trim();
            String area = (String) telaAddProcessoView.getAreaCombo().getSelectedItem();
            String vara = telaAddProcessoView.getVaraInput().getText().trim();
            String descricao = telaAddProcessoView.getDescricaoInput().getText().trim();
            String status = (String) telaAddProcessoView.getStatusCombo().getSelectedItem();
            String dataAbertura = telaAddProcessoView.getDataAberturaInput().getText().trim();
            ClienteModel clienteSelecionado = (ClienteModel) telaAddProcessoView.getClienteCombo().getSelectedItem();

            if (numero.isEmpty()) throw new CampoVazioException("Número do Processo");
            if (vara.isEmpty()) throw new CampoVazioException("Vara / Tribunal");
            if (descricao.isEmpty()) throw new CampoVazioException("Descrição");
            if (dataAbertura.isEmpty()) throw new CampoVazioException("Data de Abertura");
            if (clienteSelecionado == null) throw new CampoVazioException("Cliente");

            if (!dataAbertura.matches("\\d{2}/\\d{2}/\\d{4}"))
                throw new FormatoInvalidoException("Data de Abertura", "DD/MM/AAAA");

            if (processoDAO.verificaNumeroRepetido(numero)) throw new RegistroDuplicadoException("número", numero);

            // Passa o objeto ClienteModel diretamente — JPA cuida da FK cliente_id
            ProcessoModel processo = new ProcessoModel(numero, area, vara, descricao, status, dataAbertura, clienteSelecionado);
            processoDAO.inserir(processo);
            telaProcessoController.atualizarTabela();
            exibirSucesso("Processo \"" + numero + "\" cadastrado com sucesso!");

        } catch (CampoVazioException | FormatoInvalidoException | RegistroDuplicadoException ex) {
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
