package View;

import javax.swing.*;
import java.awt.*;

public class TelaAddProcessoView extends JFrame {

    private JButton addProcessoBTN;

    private JTextField numeroInput;
    private JTextField descricaoInput;
    private JComboBox<String> statusCombo;
    private JTextField idClienteInput;

    public TelaAddProcessoView() {

        setTitle("Adicionar Processo");
        setSize(500, 380);
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

        JLabel titulo = new JLabel("Adicionar Processo");
        titulo.setFont(tituloFont);

        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        // ================= INPUTS =================
        numeroInput = new JTextField(18);
        descricaoInput = new JTextField(18);
        idClienteInput = new JTextField(18);
        statusCombo = new JComboBox<>(new String[]{"EM ANDAMENTO", "ENCERRADO", "ARQUIVADO"});

        numeroInput.setFont(inputFont);
        descricaoInput.setFont(inputFont);
        idClienteInput.setFont(inputFont);
        statusCombo.setFont(inputFont);

        // ================= FORM =================
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        int y = 0;
        y = addField(formPanel, gbc, y, "Número:", numeroInput, labelFont);
        y = addField(formPanel, gbc, y, "Descrição:", descricaoInput, labelFont);

        // Status (JComboBox)
        JLabel labelStatus = new JLabel("Status:");
        labelStatus.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = y;
        formPanel.add(labelStatus, gbc);
        gbc.gridx = 1;
        formPanel.add(statusCombo, gbc);
        y++;

        y = addField(formPanel, gbc, y, "ID Cliente:", idClienteInput, labelFont);

        // ================= BOTÃO =================
        addProcessoBTN = new JButton("Adicionar Processo");
        addProcessoBTN.setFont(buttonFont);
        addProcessoBTN.setPreferredSize(new Dimension(220, 50));

        JPanel painelBotao = new JPanel();
        painelBotao.add(addProcessoBTN);

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
    public JButton getAddProcessoBTN() {
        return addProcessoBTN;
    }

    public JTextField getNumeroInput() {
        return numeroInput;
    }

    public JTextField getDescricaoInput() {
        return descricaoInput;
    }

    public JComboBox<String> getStatusCombo() {
        return statusCombo;
    }

    public JTextField getIdClienteInput() {
        return idClienteInput;
    }
}
