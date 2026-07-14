package Controller;

import Dao.ClienteDAO;
import Dao.PagamentoDAO;
import Dao.ProcessoDAO;
import Exception.CampoVazioException;
import Exception.FormatoInvalidoException;
import Exception.ValorNegativoException;
import Model.ClienteModel;
import Model.PagamentoModel;
import Model.ProcessoModel;
import View.TelaAddPagamentoView;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TelaEditPagamentoController {

    private final TelaAddPagamentoView telaEditPagamentoView;
    private final PagamentoDAO pagamentoDAO;
    private final ClienteDAO clienteDAO;
    private final ProcessoDAO processoDAO;
    private final PagamentoModel pagamento;
    private final TelaFinanceiroController telaFinanceiroController;

    public TelaEditPagamentoController(TelaFinanceiroController telaFinanceiroController, PagamentoModel pagamento) {
        this.telaFinanceiroController = telaFinanceiroController;
        this.pagamento = pagamento;
        this.pagamentoDAO = new PagamentoDAO();
        this.clienteDAO = new ClienteDAO();
        this.processoDAO = new ProcessoDAO();
        this.telaEditPagamentoView = new TelaAddPagamentoView();

        telaEditPagamentoView.popularCombos(clienteDAO.getLista(), processoDAO.getLista());
        telaEditPagamentoView.configurarModoEdicao();
        telaEditPagamentoView.preencherPagamento(pagamento);
        configurarEventos();
    }

    private void configurarEventos() {
        telaEditPagamentoView.getAddPagamentoBTN().addActionListener(e -> editarPagamento());
    }

    private void editarPagamento() {
        try {
            String descricao = telaEditPagamentoView.getDescricao();
            BigDecimal valor = telaEditPagamentoView.getValor();
            LocalDate data = telaEditPagamentoView.getData();
            String tipo = telaEditPagamentoView.getTipo();
            String formaPgto = telaEditPagamentoView.getFormaPagamento();
            String status = telaEditPagamentoView.getStatus();
            ClienteModel clienteSelecionado = telaEditPagamentoView.getClienteSelecionado();
            ProcessoModel processoSelecionado = telaEditPagamentoView.getProcessoSelecionado();

            pagamento.setDescricao(descricao);
            pagamento.setValor(valor);
            pagamento.setData(data);
            pagamento.setTipo(tipo);
            pagamento.setFormaPagamento(formaPgto);
            pagamento.setStatus(status);
            pagamento.setCliente(clienteSelecionado);
            pagamento.setProcesso(processoSelecionado);

            pagamentoDAO.editar(pagamento);
            telaFinanceiroController.atualizarTabela();
            telaEditPagamentoView.exibirSucesso("Pagamento atualizado com sucesso!");
            telaEditPagamentoView.dispose();

        } catch (CampoVazioException | FormatoInvalidoException | ValorNegativoException ex) {
            telaEditPagamentoView.exibirErro(ex.getMessage());
        } catch (Exception ex) {
            telaEditPagamentoView.exibirErro("Erro inesperado: " + ex.getMessage());
        }
    }
}
