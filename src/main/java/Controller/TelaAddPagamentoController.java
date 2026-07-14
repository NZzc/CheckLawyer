package Controller;

import Dao.ClienteDAO;
import Dao.PagamentoDAO;
import Dao.ProcessoDAO;
import Exception.*;
import Model.ClienteModel;
import Model.PagamentoModel;
import Model.ProcessoModel;
import View.TelaAddPagamentoView;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TelaAddPagamentoController {
    private TelaAddPagamentoView telaAddPagamentoView;
    private PagamentoDAO pagamentoDAO;
    private ClienteDAO clienteDAO;
    private ProcessoDAO processoDAO;
    private TelaFinanceiroController telaFinanceiroController;

    public TelaAddPagamentoController(TelaFinanceiroController telaFinanceiroController) {
        telaAddPagamentoView = new TelaAddPagamentoView();
        pagamentoDAO = new PagamentoDAO();
        clienteDAO = new ClienteDAO();
        processoDAO = new ProcessoDAO();
        this.telaFinanceiroController = telaFinanceiroController;

        popularCombos();
        BtnAddPagamento();
    }

    private void popularCombos() {
        telaAddPagamentoView.popularCombos(clienteDAO.getLista(), processoDAO.getLista());
    }

    public void BtnAddPagamento() {
        telaAddPagamentoView.getAddPagamentoBTN().addActionListener(e -> cadastrarPagamento());
    }

    public void cadastrarPagamento() {
        try {
            // A View lê e valida cada campo, lançando a exceção correspondente
            String descricao = telaAddPagamentoView.getDescricao();
            BigDecimal valor = telaAddPagamentoView.getValor();
            LocalDate data = telaAddPagamentoView.getData();
            String tipo = telaAddPagamentoView.getTipo();
            String formaPgto = telaAddPagamentoView.getFormaPagamento();
            String status = telaAddPagamentoView.getStatus();
            ClienteModel clienteSelecionado = telaAddPagamentoView.getClienteSelecionado();
            ProcessoModel processoSelecionado = telaAddPagamentoView.getProcessoSelecionado();

            // idProcesso = 0 quando não há processo associado
            //int idProcesso = (processoSelecionado != null) ? processoSelecionado.getID() : 0;

            PagamentoModel pagamento = new PagamentoModel(descricao, valor, data, tipo, formaPgto, status, clienteSelecionado, processoSelecionado);
            pagamentoDAO.inserir(pagamento);
            telaFinanceiroController.atualizarTabela();
            exibirSucesso("Pagamento de R$ " + String.format("%.2f", valor) + " cadastrado com sucesso!");

        } catch (CampoVazioException | FormatoInvalidoException | ValorNegativoException ex) {
            exibirErro(ex.getMessage());
        } catch (Exception ex) {
            exibirErro("Erro inesperado: " + ex.getMessage());
        }
    }

    public void exibirErro(String msg) {
        telaAddPagamentoView.exibirErro(msg);
    }

    public void exibirSucesso(String msg) {
        telaAddPagamentoView.exibirSucesso(msg);
    }
}
