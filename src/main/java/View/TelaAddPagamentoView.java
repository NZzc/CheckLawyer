package View;

import javax.swing.*;
import java.awt.*;

public class TelaAddPagamentoView extends JFrame {

    private JButton addPagamentoBTN;

    private JTextField descricaoInput;
    private JTextField valorInput;
    private JTextField dataInput;
    private JComboBox<String> tipoCombo;
    private JTextField idClienteInput;

    public TelaAddPagamentoView() {

        setTitle("Adicionar Pagamento");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        Font tituloFont = new Font("Arial", Font.BOLD, 28);
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JLabel titulo = new JLabel("Adicionar Pagamento");
        titulo.setFont(tituloFont);

        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        // ================= INPUTS =================
        descricaoInput = new JTextField(18);
        valorInput = new JTextField(18);
        dataInput = new JTextField(18);
        idClienteInput = new JTextField(18);
        tipoCombo = new JComboBox<>(new String[]{"RECEITA", "DESPESA"});

        JTextField[] inputs = {descricaoInput, valorInput, dataInput, idClienteInput};
        for (JTextField f : inputs) f.setFont(inputFont);
        tipoCombo.setFont(inputFont);

        // ================= FORM =================
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        int y = 0;
        y = addField(formPanel, gbc, y, "Descrição:", descricaoInput, labelFont);
        y = addField(formPanel, gbc, y, "Valor (R$):", valorInput, labelFont);
        y = addField(formPanel, gbc, y, "Data (DD/MM/AAAA):", dataInput, labelFont);

        // Tipo (JComboBox)
        JLabel labelTipo = new JLabel("Tipo:");
        labelTipo.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = y;
        formPanel.add(labelTipo, gbc);
        gbc.gridx = 1;
        formPanel.add(tipoCombo, gbc);
        y++;

        y = addField(formPanel, gbc, y, "ID Cliente:", idClienteInput, labelFont);

        // ================= BOTÃO =================
        addPagamentoBTN = new JButton("Adicionar Pagamento");
        addPagamentoBTN.setFont(buttonFont);
        addPagamentoBTN.setPreferredSize(new Dimension(230, 50));

        JPanel painelBotao = new JPanel();
        painelBotao.add(addPagamentoBTN);

        // ================= LAYOUT FINAL =================
        JPanel main = new JPanel(new BorderLayout());
        main.add(painelTitulo, BorderLayout.NORTH);
        main.add(formPanel, BorderLayout.CENTER);
        main.add(painelBotao, BorderLayout.SOUTH);

        setContentPane(main);
    }

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private int addField(JPanel panel, GridBagConstraints gbc, int y, String labelText, JTextField field, Font labelFont) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);

        return y + 1;
    }

    // ================= GETTERS =================
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

    public JTextField getIdClienteInput() {
        return idClienteInput;
    }
}
