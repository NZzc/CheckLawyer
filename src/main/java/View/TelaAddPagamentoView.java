package View;

import Model.ClienteModel;
import Model.ProcessoModel;

import javax.swing.*;
import java.awt.*;

public class TelaAddPagamentoView extends JFrame {

    private JButton addPagamentoBTN;

    private JTextField descricaoInput;
    private JTextField valorInput;
    private JTextField dataInput;
    private JComboBox<String> tipoCombo;
    private JComboBox<String> formaPagamentoCombo;
    private JComboBox<String> statusCombo;
    private JComboBox<ClienteModel> clienteCombo;
    private JComboBox<ProcessoModel> processoCombo;

    public TelaAddPagamentoView() {
        setTitle("Adicionar Pagamento");
        setSize(540, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        Font tituloFont = new Font("Arial", Font.BOLD, 26);
        Font labelFont = new Font("Arial", Font.BOLD, 13);
        Font inputFont = new Font("Arial", Font.PLAIN, 13);
        Font buttonFont = new Font("Arial", Font.BOLD, 15);

        JLabel titulo = new JLabel("Adicionar Pagamento");
        titulo.setFont(tituloFont);
        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        // ===== INPUTS =====
        descricaoInput = new JTextField(18);
        valorInput = new JTextField(18);
        dataInput = new JTextField(18);

        tipoCombo = new JComboBox<>(new String[]{"RECEITA", "DESPESA"});
        formaPagamentoCombo = new JComboBox<>(new String[]{"PIX", "Boleto", "Dinheiro", "Transferência", "Cartão"});
        statusCombo = new JComboBox<>(new String[]{"PAGO", "PENDENTE", "ATRASADO"});
        clienteCombo = new JComboBox<>();
        processoCombo = new JComboBox<>();

        JTextField[] inputs = {descricaoInput, valorInput, dataInput};
        for (JTextField f : inputs) f.setFont(inputFont);
        JComboBox<?>[] combos = {tipoCombo, formaPagamentoCombo, statusCombo, clienteCombo, processoCombo};
        for (JComboBox<?> c : combos) c.setFont(inputFont);

        // ===== FORM =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();
        int y = 0;

        y = addField(formPanel, gbc, y, "Descrição / Honorário:", descricaoInput, labelFont);
        y = addField(formPanel, gbc, y, "Valor (R$):", valorInput, labelFont);
        y = addField(formPanel, gbc, y, "Data (DD/MM/AAAA):", dataInput, labelFont);
        y = addCombo(formPanel, gbc, y, "Tipo:", tipoCombo, labelFont);
        y = addCombo(formPanel, gbc, y, "Forma de Pagamento:", formaPagamentoCombo, labelFont);
        y = addCombo(formPanel, gbc, y, "Status:", statusCombo, labelFont);
        y = addCombo(formPanel, gbc, y, "Cliente:", clienteCombo, labelFont);
        y = addCombo(formPanel, gbc, y, "Processo:", processoCombo, labelFont);

        // ===== BOTÃO =====
        addPagamentoBTN = new JButton("Adicionar Pagamento");
        addPagamentoBTN.setFont(buttonFont);
        addPagamentoBTN.setPreferredSize(new Dimension(230, 45));

        JPanel painelBotao = new JPanel();
        painelBotao.add(addPagamentoBTN);

        JPanel main = new JPanel(new BorderLayout());
        main.add(painelTitulo, BorderLayout.NORTH);
        main.add(formPanel, BorderLayout.CENTER);
        main.add(painelBotao, BorderLayout.SOUTH);
        setContentPane(main);
    }

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 6, 5, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private int addField(JPanel p, GridBagConstraints gbc, int y, String txt, JTextField f, Font lf) {
        JLabel l = new JLabel(txt);
        l.setFont(lf);
        gbc.gridx = 0;
        gbc.gridy = y;
        p.add(l, gbc);
        gbc.gridx = 1;
        p.add(f, gbc);
        return y + 1;
    }

    private int addCombo(JPanel p, GridBagConstraints gbc, int y, String txt, JComboBox<?> c, Font lf) {
        JLabel l = new JLabel(txt);
        l.setFont(lf);
        gbc.gridx = 0;
        gbc.gridy = y;
        p.add(l, gbc);
        gbc.gridx = 1;
        p.add(c, gbc);
        return y + 1;
    }

    // ===== GETTERS =====
    public JButton getAddPagamentoBTN() {
        return addPagamentoBTN;
    }

    public JTextField getDescricaoInput() {
        return descricaoInput;
    }

    public JTextField getValorInput() {
        return valorInput;
    }

    public JTextField getDataInput() {
        return dataInput;
    }

    public JComboBox<String> getTipoCombo() {
        return tipoCombo;
    }

    public JComboBox<String> getFormaPagamentoCombo() {
        return formaPagamentoCombo;
    }

    public JComboBox<String> getStatusCombo() {
        return statusCombo;
    }

    public JComboBox<ClienteModel> getClienteCombo() {
        return clienteCombo;
    }

    public JComboBox<ProcessoModel> getProcessoCombo() {
        return processoCombo;
    }
}
