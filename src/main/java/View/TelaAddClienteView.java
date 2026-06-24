package View;

import javax.swing.*;
import java.awt.*;

public class TelaAddClienteView extends JFrame {

    private JButton addClienteBTN;

    // ===== CLIENTE =====
    private JTextField nomeClienteInput;
    private JTextField cpfClienteInput;
    private JTextField cnpjClienteInput;
    private JTextField telefoneClienteInput;
    private JTextField emailClienteInput;
    private JTextField observacaoClienteInput;

    // ===== ENDEREÇO =====
    private JTextField ruaInput;
    private JTextField numeroInput;
    private JTextField bairroInput;
    private JTextField cidadeInput;
    private JTextField ufInput;
    private JTextField cepInput;

    public TelaAddClienteView() {

        setTitle("Adicionar Cliente");
        setSize(900, 650);
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

        JLabel titulo = new JLabel("Adicionar Cliente");
        titulo.setFont(tituloFont);

        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        // ======================
        // INPUTS CLIENTE
        // ======================
        nomeClienteInput = new JTextField(18);
        cpfClienteInput = new JTextField(18);
        cnpjClienteInput = new JTextField(18);
        telefoneClienteInput = new JTextField(18);
        emailClienteInput = new JTextField(18);
        observacaoClienteInput = new JTextField(18);

        JTextField[] clienteInputs = {
                nomeClienteInput, cpfClienteInput, cnpjClienteInput,
                telefoneClienteInput, emailClienteInput, observacaoClienteInput
        };

        for (JTextField f : clienteInputs) f.setFont(inputFont);

        JPanel clientePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        int y = 0;

        y = addField(clientePanel, gbc, y, "Nome:", nomeClienteInput, labelFont);
        y = addField(clientePanel, gbc, y, "CPF:", cpfClienteInput, labelFont);
        y = addField(clientePanel, gbc, y, "CNPJ:", cnpjClienteInput, labelFont);
        y = addField(clientePanel, gbc, y, "Telefone:", telefoneClienteInput, labelFont);
        y = addField(clientePanel, gbc, y, "Email:", emailClienteInput, labelFont);
        y = addField(clientePanel, gbc, y, "Observação:", observacaoClienteInput, labelFont);

        // ======================
        // INPUTS ENDEREÇO
        // ======================
        ruaInput = new JTextField(18);
        numeroInput = new JTextField(10);
        bairroInput = new JTextField(15);
        cidadeInput = new JTextField(15);
        ufInput = new JTextField(5);
        cepInput = new JTextField(10);

        JTextField[] endInputs = {
                ruaInput, numeroInput, bairroInput,
                cidadeInput, ufInput, cepInput
        };

        for (JTextField f : endInputs) f.setFont(inputFont);

        JPanel enderecoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = createGbc();

        int y2 = 0;

        JLabel enderecoTitulo = new JLabel("Endereço");
        enderecoTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        gbc2.gridx = 0;
        gbc2.gridy = y2++;
        gbc2.gridwidth = 2;
        enderecoPanel.add(enderecoTitulo, gbc2);
        gbc2.gridwidth = 1;

        y2 = addField(enderecoPanel, gbc2, y2, "Rua:", ruaInput, labelFont);
        y2 = addField(enderecoPanel, gbc2, y2, "Número:", numeroInput, labelFont);
        y2 = addField(enderecoPanel, gbc2, y2, "Bairro:", bairroInput, labelFont);
        y2 = addField(enderecoPanel, gbc2, y2, "Cidade:", cidadeInput, labelFont);
        y2 = addField(enderecoPanel, gbc2, y2, "UF:", ufInput, labelFont);
        y2 = addField(enderecoPanel, gbc2, y2, "CEP:", cepInput, labelFont);

        // ======================
        // QUADRANTES
        // ======================
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.add(clientePanel);
        centerPanel.add(enderecoPanel);

        // ======================
        // BOTÃO
        // ======================
        addClienteBTN = new JButton("Adicionar Cliente");
        addClienteBTN.setFont(buttonFont);
        addClienteBTN.setPreferredSize(new Dimension(220, 50));

        JPanel painelBotao = new JPanel();
        painelBotao.add(addClienteBTN);

        // ======================
        // LAYOUT FINAL
        // ======================
        JPanel main = new JPanel(new BorderLayout());
        main.add(painelTitulo, BorderLayout.NORTH);
        main.add(centerPanel, BorderLayout.CENTER);
        main.add(painelBotao, BorderLayout.SOUTH);

        setContentPane(main);
    }

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private int addField(JPanel panel, GridBagConstraints gbc, int y,
                         String labelText, JTextField field, Font labelFont) {

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);

        return y + 1;
    }

    // ===== GETTERS =====
    public JButton getAddClienteBTN() {
        return addClienteBTN;
    }

    public JTextField getNomeClienteInput() {
        return nomeClienteInput;
    }

    public JTextField getCpfClienteInput() {
        return cpfClienteInput;
    }

    public JTextField getCnpjClienteInput() {
        return cnpjClienteInput;
    }

    public JTextField getTelefoneClienteInput() {
        return telefoneClienteInput;
    }

    public JTextField getEmailClienteInput() {
        return emailClienteInput;
    }

    public JTextField getObservacaoClienteInput() {
        return observacaoClienteInput;
    }

    public JTextField getRuaInput() {
        return ruaInput;
    }

    public JTextField getNumeroInput() {
        return numeroInput;
    }

    public JTextField getBairroInput() {
        return bairroInput;
    }

    public JTextField getCidadeInput() {
        return cidadeInput;
    }

    public JTextField getUfInput() {
        return ufInput;
    }

    public JTextField getCepInput() {
        return cepInput;
    }
}