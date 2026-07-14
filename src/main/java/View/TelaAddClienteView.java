package View;

import Exception.*;
import Model.ClienteFisicoModel;
import Model.ClienteJuridicoModel;
import Model.ClienteModel;
import Model.EnderecoModel;

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
    private JLabel tituloLabel;

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
        tituloLabel = new JLabel("Adicionar Cliente", SwingConstants.CENTER);
        tituloLabel.setFont(FONT_TITULO);
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(tituloLabel);

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
    public void exibirErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro de validação", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void configurarModoEdicao() {
        setTitle("Editar Cliente");
        tituloLabel.setText("Editar Cliente");
        addClienteBTN.setText("Salvar Alterações");
    }

    public void preencherCliente(ClienteModel cliente) {
        if (cliente instanceof ClienteFisicoModel) {
            pessoaFisicaRB.setSelected(true);
            cardLayout.show(cardPanel, CARD_PF);
            nomeInput.setText(cliente.getNome());
            cpfInput.setText(cliente.getDocumento());
        } else if (cliente instanceof ClienteJuridicoModel) {
            pessoaJuridicaRB.setSelected(true);
            cardLayout.show(cardPanel, CARD_PJ);
            NomeEmpresaInput.setText(cliente.getNome());
            cnpjInput.setText(cliente.getDocumento());
        }

        telefonePFInput.setText(cliente.getTelefone());
        telefonePJInput.setText(cliente.getTelefone());
        emailPFInput.setText(cliente.getEmail());
        emailPJInput.setText(cliente.getEmail());
        observacaoPFInput.setText(cliente.getObservacao());
        observacaoPJInput.setText(cliente.getObservacao());

        EnderecoModel endereco = cliente.getEndereco();
        if (endereco != null) {
            ruaInput.setText(endereco.getRua());
            numeroInput.setText(endereco.getNumero());
            bairroInput.setText(endereco.getBairro());
            cidadeInput.setText(endereco.getCidade());
            ufInput.setText(endereco.getUf());
            cepInput.setText(endereco.getCep());
        }

        pessoaFisicaRB.setEnabled(false);
        pessoaJuridicaRB.setEnabled(false);
        cpfInput.setEnabled(false);
        cnpjInput.setEnabled(false);
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

    //GETTERS — PESSOA FÍSICA (validam e lançam as exceções)
    public String getNomeInput() throws CampoVazioException {
        String nome = nomeInput.getText().trim();
        if (nome.isEmpty()) throw new CampoVazioException("Nome");
        return nome;
    }

    public String getCpfInput() throws CampoVazioException, FormatoInvalidoException {
        String cpf = cpfInput.getText().trim();
        if (cpf.isEmpty()) throw new CampoVazioException("CPF");
        if (!cpf.matches("^\\d{11}$")) throw new FormatoInvalidoException("CPF", "11 dígitos (ex: 01234567890)");
        return cpf;
    }

    public String getTelefonePFInput() throws CampoVazioException, FormatoInvalidoException {
        String telefone = telefonePFInput.getText().trim();
        if (telefone.isEmpty()) throw new CampoVazioException("Telefone");
        if (!telefone.matches("^\\d{10,11}$")) throw new FormatoInvalidoException("Telefone", "10 ou 11 dígitos");
        return telefone;
    }

    public String getEmailPFInput() throws CampoVazioException, FormatoInvalidoException {
        String email = emailPFInput.getText().trim();
        if (email.isEmpty()) throw new CampoVazioException("Email");
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            throw new FormatoInvalidoException("Email", "xxxxx@xxxxx.com");
        return email;
    }

    public String getObservacaoPFInput() {
        return observacaoPFInput.getText().trim();
    }

    //GETTERS — PESSOA JURÍDICA (validam e lançam as exceções)
    public String getNomeEmpresaInput() throws CampoVazioException {
        String nome = NomeEmpresaInput.getText().trim();
        if (nome.isEmpty()) throw new CampoVazioException("Nome Fantasia");
        return nome;
    }

    public String getCnpjInput() throws CampoVazioException, FormatoInvalidoException {
        String cnpj = cnpjInput.getText().trim();
        if (cnpj.isEmpty()) throw new CampoVazioException("CNPJ");
        if (!cnpj.matches("^\\d{14}$")) throw new FormatoInvalidoException("CNPJ", "14 dígitos");
        return cnpj;
    }

    public String getTelefonePJInput() throws CampoVazioException, FormatoInvalidoException {
        String telefone = telefonePJInput.getText().trim();
        if (telefone.isEmpty()) throw new CampoVazioException("Telefone");
        if (!telefone.matches("^\\d{10,11}$")) throw new FormatoInvalidoException("Telefone", "10 ou 11 dígitos");
        return telefone;
    }

    public String getEmailPJInput() throws CampoVazioException, FormatoInvalidoException {
        String email = emailPJInput.getText().trim();
        if (email.isEmpty()) throw new CampoVazioException("Email");
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            throw new FormatoInvalidoException("Email", "xxxxx@xxxxx.com");
        return email;
    }

    public String getObservacaoPJInput() {
        return observacaoPJInput.getText().trim();
    }

    //GETTERS — ENDERECO (validam e lançam as exceções)
    public String getRuaInput() throws CampoVazioException {
        String rua = ruaInput.getText().trim();
        if (rua.isEmpty()) throw new CampoVazioException("Rua");
        return rua;
    }

    public String getNumeroInput() throws CampoVazioException {
        String numero = numeroInput.getText().trim();
        if (numero.isEmpty()) throw new CampoVazioException("Número");
        return numero;
    }

    public String getBairroInput() throws CampoVazioException {
        String bairro = bairroInput.getText().trim();
        if (bairro.isEmpty()) throw new CampoVazioException("Bairro");
        return bairro;
    }

    public String getCidadeInput() throws CampoVazioException {
        String cidade = cidadeInput.getText().trim();
        if (cidade.isEmpty()) throw new CampoVazioException("Cidade");
        return cidade;
    }

    public String getUfInput() throws CampoVazioException {
        String uf = ufInput.getText().trim();
        if (uf.isEmpty()) throw new CampoVazioException("UF");
        return uf;
    }

    public String getCepInput() throws CampoVazioException, FormatoInvalidoException {
        String cep = cepInput.getText().trim();
        if (cep.isEmpty()) throw new CampoVazioException("CEP");
        if (!cep.matches("^\\d{8}$")) throw new FormatoInvalidoException("CEP", "8 dígitos (ex: 00000000)");
        return cep;
    }

    //GETTER — BOTAO
    public JButton getAddClienteBTN() {
        return addClienteBTN;
    }
}
