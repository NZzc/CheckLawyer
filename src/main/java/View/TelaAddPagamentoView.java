package View;

import Model.ClienteModel;
import Model.ProcessoModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
    //Helper
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
    //Helper
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

    // ===== POPULAÇÃO DE COMBOS =====
    public void popularCombos(List<ClienteModel> clientes, List<ProcessoModel> processos) {
        clienteCombo.removeAllItems();
        processoCombo.removeAllItems();

        for (ClienteModel c : clientes) clienteCombo.addItem(c);
        for (ProcessoModel p : processos) processoCombo.addItem(p);

        if (clienteCombo.getItemCount() == 0) {
            exibirAviso("Nenhum cliente cadastrado. Cadastre um cliente antes de registrar um pagamento.");
        }
    }

    // ===== MENSAGENS =====
    public void exibirAviso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    public void exibirErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro de validação", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    // ===== LEITURA DE DADOS =====
    public String getDescricao() {
        return descricaoInput.getText().trim();
    }

    public String getValor() {
        return valorInput.getText().trim();
    }

    public String getData() {
        return dataInput.getText().trim();
    }

    public String getTipo() {
        return (String) tipoCombo.getSelectedItem();
    }

    public String getFormaPagamento() {
        return (String) formaPagamentoCombo.getSelectedItem();
    }

    public String getStatus() {
        return (String) statusCombo.getSelectedItem();
    }

    public ClienteModel getClienteSelecionado() {
        return (ClienteModel) clienteCombo.getSelectedItem();
    }

    public ProcessoModel getProcessoSelecionado() {
        return (ProcessoModel) processoCombo.getSelectedItem();
    }

    // ===== GETTER — BOTAO =====
    public JButton getAddPagamentoBTN() {
        return addPagamentoBTN;
    }
}
