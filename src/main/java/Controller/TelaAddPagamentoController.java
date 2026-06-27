package Controller;

import Dao.PagamentoDAO;
import Exception.*;
import Model.PagamentoModel;
import View.TelaAddPagamentoView;

import javax.swing.*;

public class TelaAddPagamentoController {
    private TelaAddPagamentoView telaAddPagamentoView;
    private PagamentoDAO pagamentoDAO;
    private TelaFinanceiroController telaFinanceiroController;

    public TelaAddPagamentoController(TelaFinanceiroController telaFinanceiroController) {
        telaAddPagamentoView = new TelaAddPagamentoView();
        pagamentoDAO = new PagamentoDAO();
        this.telaFinanceiroController = telaFinanceiroController;
        configurarEventos();
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
            String idClienteStr = telaAddPagamentoView.getIdClienteInput().getText().trim();

            // Campos obrigatórios
            if (descricao.isEmpty()) throw new CampoVazioException("Descrição");
            if (valorStr.isEmpty()) throw new CampoVazioException("Valor");
            if (data.isEmpty()) throw new CampoVazioException("Data");
            if (idClienteStr.isEmpty()) throw new CampoVazioException("ID do Cliente");

            // Formato de data DD/MM/AAAA
            if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
                throw new FormatoInvalidoException("Data", "DD/MM/AAAA");
            }
            String[] partesData = data.split("/");
            int dia = Integer.parseInt(partesData[0]);
            int mes = Integer.parseInt(partesData[1]);
            int ano = Integer.parseInt(partesData[2]);
            if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 1900) {
                throw new FormatoInvalidoException("Data", "data válida no formato DD/MM/AAAA");
            }

            // Valor: numérico e positivo
            double valor;
            try {
                valor = Double.parseDouble(valorStr.replace(",", "."));
            } catch (NumberFormatException ex) {
                throw new FormatoInvalidoException("Valor", "número decimal (ex: 150.00 ou 150,00)");
            }
            if (valor <= 0) throw new ValorNegativoException("Valor");

            // ID do cliente: numérico e positivo
            int idCliente;
            try {
                idCliente = Integer.parseInt(idClienteStr);
            } catch (NumberFormatException ex) {
                throw new FormatoInvalidoException("ID do Cliente", "número inteiro positivo");
            }
            if (idCliente <= 0) throw new ValorNegativoException("ID do Cliente");

            PagamentoModel pagamento = new PagamentoModel(descricao, valor, data, tipo, idCliente);
            pagamentoDAO.addPagamento(pagamento);
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
