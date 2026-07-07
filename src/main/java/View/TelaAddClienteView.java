package View;

import javax.swing.*;
import java.awt.*;

public class TelaAddClienteView extends JFrame {

    // ===== TIPO DE CLIENTE =====
    private JRadioButton pessoaFisicaRB;
    private JRadioButton pessoaJuridicaRB;

    // ===== CARDLAYOUT =====
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private static final String CARD_PF = "PessoaFisica";
    private static final String CARD_PJ = "PessoaJuridica";

    // ===== CAMPOS PESSOA FÍSICA =====
    private JTextField nomeInput;
    private JTextField cpfInput;
    private JTextField telefonePFInput;
    private JTextField emailPFInput;
    private JTextField observacaoPFInput;

    // ===== CAMPOS PESSOA JURÍDICA =====
    private JTextField NomeEmpresaInput;
    private JTextField cnpjInput;
    private JTextField telefonePJInput;
    private JTextField emailPJInput;
    private JTextField observacaoPJInput;

    // ===== CAMPOS ENDEREÇO =====
    private JTextField ruaInput;
    private JTextField numeroInput;
    private JTextField bairroInput;
    private JTextField cidadeInput;
    private JTextField ufInput;
    private JTextField cepInput;

    // ===== BOTÃO =====
    private JButton addClienteBTN;

    // ===== FONTES =====
    private static final Font FONT_TITULO = new Font("Arial", Font.BOLD, 28);
    private static final Font FONT_SUBTITULO = new Font("Arial", Font.BOLD, 18);
    private static final Font FONT_LABEL = new Font("Arial", Font.BOLD, 14);
    private static final Font FONT_INPUT = new Font("Arial", Font.PLAIN, 14);
    private static final Font FONT_BOTAO = new Font("Arial", Font.BOLD, 16);

    public TelaAddClienteView() {
        setTitle("Adicionar Cliente");
        setSize(900, 680);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel main = new JPanel(new BorderLayout(0, 8));
        main.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        main.add(criarPainelTitulo(), BorderLayout.NORTH);
        main.add(criarPainelCentral(), BorderLayout.CENTER);
        main.add(criarPainelBotao(), BorderLayout.SOUTH);

        setContentPane(main);
    }

    // PAINEL TÍTULO + SELEÇÃO DE TIPO
    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Título
        JLabel titulo = new JLabel("Adicionar Cliente", SwingConstants.CENTER);
        titulo.setFont(FONT_TITULO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(8));

        // Seleção de tipo
        painel.add(criarPainelTipoCliente());

        painel.add(Box.createVerticalStrut(4));

        // Separador
        painel.add(new JSeparator());

        return painel;
    }

    // PAINEL TIPO DE CLIENTE (RadioButtons)
    private JPanel criarPainelTipoCliente() {
        pessoaFisicaRB = new JRadioButton("Pessoa Física", true);
        pessoaJuridicaRB = new JRadioButton("Pessoa Jurídica", false);

        pessoaFisicaRB.setFont(FONT_LABEL);
        pessoaJuridicaRB.setFont(FONT_LABEL);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(pessoaFisicaRB);
        grupo.add(pessoaJuridicaRB);

        // Ao mudar o RadioButton, troca o card
        pessoaFisicaRB.addActionListener(e -> cardLayout.show(cardPanel, CARD_PF));
        pessoaJuridicaRB.addActionListener(e -> cardLayout.show(cardPanel, CARD_PJ));

        JLabel labelTipo = new JLabel("Tipo de Cliente:");
        labelTipo.setFont(FONT_LABEL);

        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 4));
        painel.add(labelTipo);
        painel.add(pessoaFisicaRB);
        painel.add(pessoaJuridicaRB);

        return painel;
    }

    // PAINEL CENTRAL (formulário esquerda + endereço direita)
    private JPanel criarPainelCentral() {
        // CardLayout para alternar PF / PJ
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(criarPainelPessoaFisica(), CARD_PF);
        cardPanel.add(criarPainelPessoaJuridica(), CARD_PJ);

        cardLayout.show(cardPanel, CARD_PF);

        JPanel painel = new JPanel(new GridLayout(1, 2, 20, 0));
        painel.add(cardPanel);
        painel.add(criarPainelEndereco());

        return painel;
    }

    // FORMULÁRIO PESSOA FÍSICA
    private JPanel criarPainelPessoaFisica() {
        nomeInput = new JTextField(18);
        cpfInput = new JTextField(18);
        telefonePFInput = new JTextField(18);
        emailPFInput = new JTextField(18);
        observacaoPFInput = new JTextField(18);

        aplicarFonteInputs(nomeInput, cpfInput, telefonePFInput,
                emailPFInput, observacaoPFInput);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = criarGbc();
        int y = 0;

        y = adicionarSubtitulo(painel, gbc, y, "Dados do Cliente");
        y = adicionarCampo(painel, gbc, y, "Nome:", nomeInput);
        y = adicionarCampo(painel, gbc, y, "CPF:", cpfInput);
        y = adicionarCampo(painel, gbc, y, "Telefone:", telefonePFInput);
        y = adicionarCampo(painel, gbc, y, "Email:", emailPFInput);
        y = adicionarCampo(painel, gbc, y, "Observação:", observacaoPFInput);

        preencherEspaco(painel, gbc, y);

        return painel;
    }

    // FORMULÁRIO PESSOA JURÍDICA
    private JPanel criarPainelPessoaJuridica() {
        NomeEmpresaInput = new JTextField(18);
        cnpjInput = new JTextField(18);
        telefonePJInput = new JTextField(18);
        emailPJInput = new JTextField(18);
        observacaoPJInput = new JTextField(18);

        aplicarFonteInputs(NomeEmpresaInput, cnpjInput,
                telefonePJInput, emailPJInput, observacaoPJInput);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = criarGbc();
        int y = 0;

        y = adicionarSubtitulo(painel, gbc, y, "Dados do Cliente");
        y = adicionarCampo(painel, gbc, y, "Nome Fantasia:", NomeEmpresaInput);
        y = adicionarCampo(painel, gbc, y, "CNPJ:", cnpjInput);
        y = adicionarCampo(painel, gbc, y, "Telefone:", telefonePJInput);
        y = adicionarCampo(painel, gbc, y, "Email:", emailPJInput);
        y = adicionarCampo(painel, gbc, y, "Observação:", observacaoPJInput);

        preencherEspaco(painel, gbc, y);

        return painel;
    }

    // PAINEL ENDEREÇO (compartilhado)
    private JPanel criarPainelEndereco() {
        ruaInput = new JTextField(18);
        numeroInput = new JTextField(10);
        bairroInput = new JTextField(15);
        cidadeInput = new JTextField(15);
        ufInput = new JTextField(5);
        cepInput = new JTextField(10);

        aplicarFonteInputs(ruaInput, numeroInput, bairroInput,
                cidadeInput, ufInput, cepInput);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = criarGbc();
        int y = 0;

        y = adicionarSubtitulo(painel, gbc, y, "Endereço");
        y = adicionarCampo(painel, gbc, y, "Rua:", ruaInput);
        y = adicionarCampo(painel, gbc, y, "Número:", numeroInput);
        y = adicionarCampo(painel, gbc, y, "Bairro:", bairroInput);
        y = adicionarCampo(painel, gbc, y, "Cidade:", cidadeInput);
        y = adicionarCampo(painel, gbc, y, "UF:", ufInput);
        y = adicionarCampo(painel, gbc, y, "CEP:", cepInput);

        preencherEspaco(painel, gbc, y);

        return painel;
    }

    // PAINEL BOTÃO
    private JPanel criarPainelBotao() {
        addClienteBTN = new JButton("Adicionar Cliente");
        addClienteBTN.setFont(FONT_BOTAO);
        addClienteBTN.setPreferredSize(new Dimension(220, 50));

        JPanel painel = new JPanel();
        painel.add(addClienteBTN);
        return painel;
    }

    // UTILITÁRIOS DE LAYOUT
    private GridBagConstraints criarGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    //Adiciona um subtítulo de seção ocupando as duas colunas do GridBagLayout.
    private int adicionarSubtitulo(JPanel painel, GridBagConstraints gbc, int y, String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONT_SUBTITULO);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        painel.add(label, gbc);

        gbc.gridwidth = 1; // reseta para o padrão
        return y + 1;
    }

    //Adiciona um par label + campo ao painel usando GridBagLayout.
    private int adicionarCampo(JPanel painel, GridBagConstraints gbc, int y,
                               String textoLabel, JTextField campo) {
        JLabel label = new JLabel(textoLabel);
        label.setFont(FONT_LABEL);

        gbc.gridx = 0;
        gbc.gridy = y;
        painel.add(label, gbc);

        gbc.gridx = 1;
        painel.add(campo, gbc);

        return y + 1;
    }

    private void preencherEspaco(JPanel painel, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        painel.add(Box.createVerticalGlue(), gbc);
        gbc.weighty = 0;
        gbc.gridwidth = 1;
    }

    //===================================================================
    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }






    //===================================================================



    private void aplicarFonteInputs(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setFont(FONT_INPUT);
        }
    }





    // GETTERS — TIPO

    //Retorna true se Pessoa Física estiver selecionada.
    public boolean isPessoaFisica() {
        return pessoaFisicaRB.isSelected();
    }

    //GETTERS — PESSOA FÍSICA
    public String getNomeInput() {
        return nomeInput.getText();
    }

    public String getCpfInput() {
        return cpfInput.getText();
    }

    public String getTelefonePFInput() {
        return telefonePFInput.getText();
    }

    public String getEmailPFInput() {
        return emailPFInput.getText();
    }

    public String getObservacaoPFInput() {
        return observacaoPFInput.getText();
    }

    //GETTERS — PESSOA JURÍDICA
    public String getNomeEmpresaInput() {
        return NomeEmpresaInput.getText();
    }

    public String getCnpjInput() {
        return cnpjInput.getText();
    }

    public String getTelefonePJInput() {
        return telefonePJInput.getText();
    }

    public String getEmailPJInput() {
        return emailPJInput.getText();
    }

    public String getObservacaoPJInput() {
        return observacaoPJInput.getText();
    }

    //GETTERS — ENDERECO
    public String getRuaInput() {
        return ruaInput.getText();
    }

    public String getNumeroInput() {
        return numeroInput.getText();
    }

    public String getBairroInput() {
        return bairroInput.getText();
    }

    public String getCidadeInput() {
        return cidadeInput.getText();
    }

    public String getUfInput() {
        return ufInput.getText();
    }

    public String getCepInput() {
        return cepInput.getText();
    }

    //GETTER — BOTAO
    public JButton getAddClienteBTN() {
        return addClienteBTN;
    }
}