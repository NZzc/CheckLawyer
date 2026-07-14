package Controller;

import Dao.ClienteDAO;
import Dao.ProcessoDAO;
import Exception.CampoVazioException;
import Exception.FormatoInvalidoException;
import Exception.RegistroDuplicadoException;
import Model.ClienteModel;
import Model.ProcessoModel;
import View.TelaAddProcessoView;

public class TelaEditProcessoController {

    private TelaAddProcessoView telaEditProcessoView;
    private ProcessoDAO processoDAO;
    private ClienteDAO clienteDAO;
    private ProcessoModel processo;
    private TelaProcessoController telaProcessoController;

    public TelaEditProcessoController(TelaProcessoController telaProcessoController, ProcessoModel processo) {
        this.telaProcessoController = telaProcessoController;
        this.processo = processo;
        this.processoDAO = new ProcessoDAO();
        this.clienteDAO = new ClienteDAO();
        this.telaEditProcessoView = new TelaAddProcessoView();

        telaEditProcessoView.popularClientes(clienteDAO.getLista());
        telaEditProcessoView.configurarModoEdicao();
        telaEditProcessoView.preencherProcesso(processo);
        configurarEventos();
    }

    private void configurarEventos() {
        telaEditProcessoView.getAddProcessoBTN().addActionListener(e -> editarProcesso());
    }

    private void editarProcesso() {
        try {
            String numero = telaEditProcessoView.getNumero();

            if (!numero.equalsIgnoreCase(processo.getNumero()) && processoDAO.verificaNumeroRepetido(numero)) {
                throw new RegistroDuplicadoException("número", numero);
            }

            processo.setNumero(numero);
            processo.setArea(telaEditProcessoView.getArea());
            processo.setVara(telaEditProcessoView.getVara());
            processo.setDescricao(telaEditProcessoView.getDescricao());
            processo.setStatus(telaEditProcessoView.getStatus());
            processo.setDataAbertura(telaEditProcessoView.getDataAbertura());

            ClienteModel clienteSelecionado = telaEditProcessoView.getClienteSelecionado();
            processo.setCliente(clienteSelecionado);

            processoDAO.editar(processo);
            telaProcessoController.atualizarTabela();
            telaEditProcessoView.exibirSucesso("Processo atualizado com sucesso!");
            telaEditProcessoView.dispose();

        } catch (CampoVazioException | FormatoInvalidoException | RegistroDuplicadoException ex) {
            telaEditProcessoView.exibirErro(ex.getMessage());
        } catch (Exception ex) {
            telaEditProcessoView.exibirErro("Erro inesperado: " + ex.getMessage());
        }
    }
}
