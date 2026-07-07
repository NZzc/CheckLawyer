package View;

import Model.ClienteModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaAddProcessoView extends JFrame {

    private JButton addProcessoBTN;

    private JTextField numeroInput;
    private JComboBox<String> areaCombo;
    private JTextField varaInput;
    private JTextField descricaoInput;
    private JComboBox<String> statusCombo;
    private JTextField dataAberturaInput;
    private JComboBox<ClienteModel> clienteCombo;

    public TelaAddProcessoView() {
        setTitle("Adicionar Processo");
        setSize(540, 460);
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

        JLabel titulo = new JLabel("Adicionar Processo");
        titulo.setFont(tituloFont);
        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        // ===== INPUTS =====
        numeroInput = new JTextField(18);
        varaInput = new JTextField(18);
        descricaoInput = new JTextField(18);
        dataAberturaInput = new JTextField(18);

        areaCombo = new JComboBox<>(new String[]{"Cível", "Trabalhista", "Criminal", "Família", "Tributário", "Previdenciário", "Consumidor", "Outro"});
        statusCombo = new JComboBox<>(new String[]{"EM ANDAMENTO", "ENCERRADO", "ARQUIVADO"});
        clienteCombo = new JComboBox<>();

        JTextField[] inputs = {numeroInput, varaInput, descricaoInput, dataAberturaInput};
        for (JTextField f : inputs) f.setFont(inputFont);
        areaCombo.setFont(inputFont);
        statusCombo.setFont(inputFont);
        clienteCombo.setFont(inputFont);

        // ===== FORM =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();
        int y = 0;

        y = addField(formPanel, gbc, y, "Número do Processo:", numeroInput, labelFont);

        y = addCombo(formPanel, gbc, y, "Área do Direito:", areaCombo, labelFont);
        y = addField(formPanel, gbc, y, "Vara / Tribunal:", varaInput, labelFont);
        y = addField(formPanel, gbc, y, "Descrição:", descricaoInput, labelFont);
        y = addCombo(formPanel, gbc, y, "Status:", statusCombo, labelFont);
        y = addField(formPanel, gbc, y, "Data Abertura (DD/MM/AAAA):", dataAberturaInput, labelFont);
        y = addCombo(formPanel, gbc, y, "Cliente:", clienteCombo, labelFont);

        // ===== BOTÃO =====
        addProcessoBTN = new JButton("Adicionar Processo");
        addProcessoBTN.setFont(buttonFont);
        addProcessoBTN.setPreferredSize(new Dimension(220, 45));

        JPanel painelBotao = new JPanel();
        painelBotao.add(addProcessoBTN);

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

    // ===== POPULAÇÃO DE COMBOS =====
    public void popularClientes(List<ClienteModel> lista) {
        clienteCombo.removeAllItems();
        for (ClienteModel c : lista) {
            clienteCombo.addItem(c);
        }
        if (clienteCombo.getItemCount() == 0) {
            exibirAviso("Nenhum cliente cadastrado.\nCadastre um cliente antes de adicionar um processo.");
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
    public String getNumero() {
        return numeroInput.getText().trim();
    }

    public String getArea() {
        return (String) areaCombo.getSelectedItem();
    }

    public String getVara() {
        return varaInput.getText().trim();
    }

    public String getDescricao() {
        return descricaoInput.getText().trim();
    }

    public String getStatus() {
        return (String) statusCombo.getSelectedItem();
    }

    public String getDataAbertura() {
        return dataAberturaInput.getText().trim();
    }

    public ClienteModel getClienteSelecionado() {
        return (ClienteModel) clienteCombo.getSelectedItem();
    }

    // ===== GETTER — BOTAO =====
    public JButton getAddProcessoBTN() {
        return addProcessoBTN;
    }
}
