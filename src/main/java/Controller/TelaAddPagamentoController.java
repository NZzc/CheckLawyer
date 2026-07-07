package Controller;

import Dao.ClienteDAO;
import Dao.PagamentoDAO;
import Dao.ProcessoDAO;
import Exception.*;
import Model.ClienteModel;
import Model.PagamentoModel;
import Model.ProcessoModel;
import View.TelaAddPagamentoView;

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
            String descricao = telaAddPagamentoView.getDescricao();
            String valorStr = telaAddPagamentoView.getValor();
            String data = telaAddPagamentoView.getData();
            String tipo = telaAddPagamentoView.getTipo();
            String formaPgto = telaAddPagamentoView.getFormaPagamento();
            String status = telaAddPagamentoView.getStatus();
            ClienteModel clienteSelecionado = telaAddPagamentoView.getClienteSelecionado();
            ProcessoModel processoSelecionado = telaAddPagamentoView.getProcessoSelecionado();

            if (descricao.isEmpty()) throw new CampoVazioException("Descrição / Honorário");
            if (valorStr.isEmpty()) throw new CampoVazioException("Valor");
            if (data.isEmpty()) throw new CampoVazioException("Data");
            if (clienteSelecionado == null) throw new CampoVazioException("Cliente");

            if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) throw new FormatoInvalidoException("Data", "DD/MM/AAAA");
            int dia = Integer.parseInt(data.split("/")[0]);
            int mes = Integer.parseInt(data.split("/")[1]);
            if (dia < 1 || dia > 31 || mes < 1 || mes > 12)
                throw new FormatoInvalidoException("Data", "data válida DD/MM/AAAA");

            double valor;
            try {
                valor = Double.parseDouble(valorStr.replace(",", "."));
            } catch (NumberFormatException ex) {
                throw new FormatoInvalidoException("Valor", "número decimal (ex: 150.00 ou 150,00)");
            }
            if (valor <= 0) throw new ValorNegativoException("Valor");

            // idProcesso = 0 quando não há processo associado
            int idProcesso = (processoSelecionado != null) ? processoSelecionado.getID() : 0;

            PagamentoModel pagamento = new PagamentoModel(descricao, valor, data, tipo, formaPgto, status, clienteSelecionado.getID(), idProcesso);
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
