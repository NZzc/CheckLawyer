package Controller;

import Dao.ClienteDAO;
import Dao.PagamentoDAO;
import Dao.ProcessoDAO;
import Exception.*;
import Model.ClienteModel;
import Model.PagamentoModel;
import Model.ProcessoModel;
import View.TelaAddPagamentoView;

import javax.swing.*;

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
        configurarEventos();
    }

    /**
     * Carrega clientes e processos nos JComboBoxes
     */
    private void popularCombos() {
        JComboBox<ClienteModel> comboC = telaAddPagamentoView.getClienteCombo();
        JComboBox<ProcessoModel> comboP = telaAddPagamentoView.getProcessoCombo();
        comboC.removeAllItems();
        comboP.removeAllItems();

        for (ClienteModel c : clienteDAO.getLista()) comboC.addItem(c);
        for (ProcessoModel p : processoDAO.getLista()) comboP.addItem(p);

        if (comboC.getItemCount() == 0)
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado. Cadastre um cliente antes de registrar um pagamento.", "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    public void configurarEventos() {
        telaAddPagamentoView.getAddPagamentoBTN().addActionListener(e -> cadastrarPagamento());
    }

    public void cadastrarPagamento() {
        try {
            String descricao = telaAddPagamentoView.getDescricaoInput().getText().trim();
            String valorStr = telaAddPagamentoView.getValorInput().getText().trim();
            String data = telaAddPagamentoView.getDataInput().getText().trim();
            String tipo = (String) telaAddPagamentoView.getTipoCombo().getSelectedItem();
            String formaPgto = (String) telaAddPagamentoView.getFormaPagamentoCombo().getSelectedItem();
            String status = (String) telaAddPagamentoView.getStatusCombo().getSelectedItem();
            ClienteModel clienteSelecionado = (ClienteModel) telaAddPagamentoView.getClienteCombo().getSelectedItem();
            ProcessoModel processoSelecionado = (ProcessoModel) telaAddPagamentoView.getProcessoCombo().getSelectedItem();

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

            // idProcesso = 0 quando não há processo associado (despesa geral)
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
        JOptionPane.showMessageDialog(null, msg, "Erro de validação", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
