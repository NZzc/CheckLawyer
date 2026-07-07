package Controller;

import Dao.ClienteDAO;
import Dao.ProcessoDAO;
import Exception.*;
import Model.ClienteModel;
import Model.ProcessoModel;
import View.TelaAddProcessoView;

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

        ComboClientes();
        BtnAddProcesso();
    }

    private void ComboClientes() {
        telaAddProcessoView.popularClientes(clienteDAO.getLista());
    }

    public void BtnAddProcesso() {
        telaAddProcessoView.getAddProcessoBTN().addActionListener(e -> cadastrarProcesso());
    }

    public void cadastrarProcesso() {
        try {
            // A View lê e valida cada campo, lançando a exceção correspondente
            String numero = telaAddProcessoView.getNumero();
            String area = telaAddProcessoView.getArea();
            String vara = telaAddProcessoView.getVara();
            String descricao = telaAddProcessoView.getDescricao();
            String status = telaAddProcessoView.getStatus();
            String dataAbertura = telaAddProcessoView.getDataAbertura();
            ClienteModel clienteSelecionado = telaAddProcessoView.getClienteSelecionado();

            // Regra que depende do banco continua no Controller (a View não acessa o DAO)
            if (processoDAO.verificaNumeroRepetido(numero)) throw new RegistroDuplicadoException("número", numero);

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
        telaAddProcessoView.exibirErro(msg);
    }

    public void exibirSucesso(String msg) {
        telaAddProcessoView.exibirSucesso(msg);
    }
}
