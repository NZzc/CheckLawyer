package View;

import Exception.SelecionarItemException;
import Model.PagamentoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class TelaFinanceiroView extends JFrame {

    private JTable tabelaPagamentos;
    private JButton addPagamentoBTN;
    private JButton editarPagamentoBTN;
    private JButton excluirPagamentoBTN;
    private JLabel saldoLabel;

    public TelaFinanceiroView() {
        setTitle("Financeiro");
        setSize(1100, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        Font tituloFont = new Font("Arial", Font.BOLD, 28);
        Font buttonFont = new Font("Arial", Font.BOLD, 15);
        Font saldoFont = new Font("Arial", Font.BOLD, 18);

        JLabel titulo = new JLabel("Financeiro");
        titulo.setFont(tituloFont);
        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        saldoLabel = new JLabel("Saldo: R$ 0,00");
        saldoLabel.setFont(saldoFont);
        JPanel painelSaldo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        painelSaldo.add(saldoLabel);

        tabelaPagamentos = new JTable(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"ID", "Descrição", "Valor (R$)", "Data", "Tipo", "Forma Pgto.", "Status", "ID Cliente", "ID Processo"}));
        tabelaPagamentos.setFont(new Font("Arial", Font.PLAIN, 13));
        tabelaPagamentos.setRowHeight(22);

        JScrollPane scrollTabela = new JScrollPane(tabelaPagamentos);

        addPagamentoBTN = new JButton("Adicionar Pagamento");
        editarPagamentoBTN = new JButton("Editar Pagamento");
        excluirPagamentoBTN = new JButton("Excluir Pagamento");
        addPagamentoBTN.setFont(buttonFont);
        editarPagamentoBTN.setFont(buttonFont);
        excluirPagamentoBTN.setFont(buttonFont);
        Dimension tamanhoBotaoAcao = new Dimension(220, 40);
        addPagamentoBTN.setPreferredSize(tamanhoBotaoAcao);
        editarPagamentoBTN.setPreferredSize(tamanhoBotaoAcao);
        excluirPagamentoBTN.setPreferredSize(tamanhoBotaoAcao);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.add(addPagamentoBTN);
        painelBotoes.add(editarPagamentoBTN);
        painelBotoes.add(excluirPagamentoBTN);

        JPanel painelNorte = new JPanel(new BorderLayout());
        painelNorte.add(painelTitulo, BorderLayout.NORTH);
        painelNorte.add(painelSaldo, BorderLayout.SOUTH);

        JPanel main = new JPanel(new BorderLayout());
        main.add(painelNorte, BorderLayout.NORTH);
        main.add(scrollTabela, BorderLayout.CENTER);
        main.add(painelBotoes, BorderLayout.SOUTH);
        setContentPane(main);
    }

    public void popularTabela(List<PagamentoModel> lista) {
        DefaultTableModel model = (DefaultTableModel) tabelaPagamentos.getModel();
        model.setRowCount(0);
        for (PagamentoModel p : lista) {
            model.addRow(new Object[]{p.getID(), p.getDescricao(), String.format("%.2f", p.getValor()), p.getData(), p.getTipo(), p.getFormaPagamento(), p.getStatus(), p.getIdCliente(), p.getIdProcesso() == 0 ? "-" : p.getIdProcesso()});
        }
    }

    public void atualizarResumo(BigDecimal saldo, BigDecimal totalReceitas, BigDecimal totalDespesas, Set<String> formasUtilizadas) {
        String texto = String.format("Saldo: R$ %.2f | Receitas: R$ %.2f | Despesas: R$ %.2f | Formas de pagamento: %s", saldo, totalReceitas, totalDespesas, String.join(", ", formasUtilizadas));
        saldoLabel.setText(texto);
    }

    public int getIDlinhaSelecionada() throws SelecionarItemException {
        int linha = tabelaPagamentos.getSelectedRow();
        if (linha == -1) throw new SelecionarItemException("pagamento");
        return Integer.parseInt(tabelaPagamentos.getValueAt(linha, 0).toString());
    }

    public boolean confirmarExclusao(String msg) {
        int ok = JOptionPane.showConfirmDialog(null, msg, "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        return ok == JOptionPane.YES_OPTION;
    }

    public void exibirErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public JButton getAddPagamentoBTN() {
        return addPagamentoBTN;
    }

    public JButton getExcluirPagamentoBTN() {
        return excluirPagamentoBTN;
    }

    public JButton getEditarPagamentoBTN() {
        return editarPagamentoBTN;
    }
}
