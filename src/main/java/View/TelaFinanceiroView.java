package View;

import javax.swing.*;
import java.awt.*;

public class TelaFinanceiroView extends JFrame {

    private JTable tabelaPagamentos;
    private JButton addPagamentoBTN;
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
        excluirPagamentoBTN = new JButton("Excluir Pagamento");
        addPagamentoBTN.setFont(buttonFont);
        excluirPagamentoBTN.setFont(buttonFont);
        addPagamentoBTN.setPreferredSize(new Dimension(220, 40));
        excluirPagamentoBTN.setPreferredSize(new Dimension(220, 40));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.add(excluirPagamentoBTN);
        painelBotoes.add(addPagamentoBTN);

        JPanel painelNorte = new JPanel(new BorderLayout());
        painelNorte.add(painelTitulo, BorderLayout.NORTH);
        painelNorte.add(painelSaldo, BorderLayout.SOUTH);

        JPanel main = new JPanel(new BorderLayout());
        main.add(painelNorte, BorderLayout.NORTH);
        main.add(scrollTabela, BorderLayout.CENTER);
        main.add(painelBotoes, BorderLayout.SOUTH);
        setContentPane(main);
    }

    public JButton getAddPagamentoBTN() {
        return addPagamentoBTN;
    }

    public JButton getExcluirPagamentoBTN() {
        return excluirPagamentoBTN;
    }

    public JTable getTabelaPagamentos() {
        return tabelaPagamentos;
    }

    public JLabel getSaldoLabel() {
        return saldoLabel;
    }
}
