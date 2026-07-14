package View;

import Model.ProcessoModel;
import Model.AudienciaModel;
import Exception.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaAddAudienciaView extends JFrame {

    private JButton addAudienciaBTN;
    private JLabel tituloLabel;

    private JTextField dataInput;
    private JTextField horaInput;
    private JTextField localInput;
    private JComboBox<String> tipoCombo;
    private JTextField descricaoInput;
    private JTextField resultadoInput;
    private JComboBox<ProcessoModel> processoCombo;

    public TelaAddAudienciaView() {
        setTitle("Adicionar Audiência");
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

        tituloLabel = new JLabel("Adicionar Audiência");
        tituloLabel.setFont(tituloFont);
        JPanel painelTitulo = new JPanel();
        painelTitulo.add(tituloLabel);

        // ===== INPUTS =====
        dataInput = new JTextField(18);
        horaInput = new JTextField(18);
        localInput = new JTextField(18);
        descricaoInput = new JTextField(18);
        resultadoInput = new JTextField(18);

        tipoCombo = new JComboBox<>(new String[]{"Instrução e Julgamento", "Conciliação", "Julgamento", "Oitiva de Testemunhas", "Perícia", "Outra"});
        processoCombo = new JComboBox<>();

        JTextField[] inputs = {dataInput, horaInput, localInput, descricaoInput, resultadoInput};
        for (JTextField f : inputs) f.setFont(inputFont);
        tipoCombo.setFont(inputFont);
        processoCombo.setFont(inputFont);

        // ===== FORM =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();
        int y = 0;

        y = addField(formPanel, gbc, y, "Data (DD/MM/AAAA):", dataInput, labelFont);
        y = addField(formPanel, gbc, y, "Hora (HH:MM):", horaInput, labelFont);
        y = addField(formPanel, gbc, y, "Local:", localInput, labelFont);
        y = addCombo(formPanel, gbc, y, "Tipo de Audiência:", tipoCombo, labelFont);
        y = addField(formPanel, gbc, y, "Pauta / Descrição:", descricaoInput, labelFont);
        y = addField(formPanel, gbc, y, "Resultado:", resultadoInput, labelFont);
        y = addCombo(formPanel, gbc, y, "Processo:", processoCombo, labelFont);

        // ===== BOTÃO =====
        addAudienciaBTN = new JButton("Adicionar Audiência");
        addAudienciaBTN.setFont(buttonFont);
        addAudienciaBTN.setPreferredSize(new Dimension(220, 45));

        JPanel painelBotao = new JPanel();
        painelBotao.add(addAudienciaBTN);

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
    public void popularProcessos(List<ProcessoModel> lista) {
        processoCombo.removeAllItems();
        for (ProcessoModel p : lista) {
            processoCombo.addItem(p);
        }
        if (processoCombo.getItemCount() == 0) {
            exibirAviso("Nenhum processo cadastrado.\nCadastre um processo antes de adicionar uma audiência.");
        }
    }

    public void configurarModoEdicao() {
        setTitle("Editar Audiência");
        tituloLabel.setText("Editar Audiência");
        addAudienciaBTN.setText("Salvar Alterações");
    }

    public void preencherAudiencia(AudienciaModel audiencia) {
        dataInput.setText(audiencia.getData());
        horaInput.setText(audiencia.getHora());
        localInput.setText(audiencia.getLocal());
        tipoCombo.setSelectedItem(audiencia.getTipo());
        descricaoInput.setText(audiencia.getDescricao());
        resultadoInput.setText(audiencia.getResultado());

        ProcessoModel processo = audiencia.getProcesso();
        if (processo != null) {
            selecionarProcesso(processo.getID());
        }
    }

    private void selecionarProcesso(int idProcesso) {
        for (int i = 0; i < processoCombo.getItemCount(); i++) {
            ProcessoModel processo = processoCombo.getItemAt(i);
            if (processo.getID() == idProcesso) {
                processoCombo.setSelectedIndex(i);
                return;
            }
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

    // ===== LEITURA DE DADOS (a View valida e lança as exceções) =====
    public String getData() throws CampoVazioException, FormatoInvalidoException {
        String data = dataInput.getText().trim();
        if (data.isEmpty()) throw new CampoVazioException("Data");
        if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) throw new FormatoInvalidoException("Data", "DD/MM/AAAA");
        int dia = Integer.parseInt(data.split("/")[0]);
        int mes = Integer.parseInt(data.split("/")[1]);
        if (dia < 1 || dia > 31 || mes < 1 || mes > 12)
            throw new FormatoInvalidoException("Data", "data válida DD/MM/AAAA");
        return data;
    }

    public String getHora() throws CampoVazioException, FormatoInvalidoException {
        String hora = horaInput.getText().trim();
        if (hora.isEmpty()) throw new CampoVazioException("Hora");
        if (!hora.matches("\\d{2}:\\d{2}")) throw new FormatoInvalidoException("Hora", "HH:MM");
        int hh = Integer.parseInt(hora.split(":")[0]);
        int mm = Integer.parseInt(hora.split(":")[1]);
        if (hh > 23 || mm > 59) throw new FormatoInvalidoException("Hora", "entre 00:00 e 23:59");
        return hora;
    }

    public String getLocal() throws CampoVazioException {
        String local = localInput.getText().trim();
        if (local.isEmpty()) throw new CampoVazioException("Local");
        return local;
    }

    public String getTipo() {
        return (String) tipoCombo.getSelectedItem();
    }

    public String getDescricao() throws CampoVazioException {
        String descricao = descricaoInput.getText().trim();
        if (descricao.isEmpty()) throw new CampoVazioException("Pauta / Descrição");
        return descricao;
    }

    public String getResultado() {
        return resultadoInput.getText().trim();
    }

    public ProcessoModel getProcessoSelecionado() throws CampoVazioException {
        ProcessoModel processo = (ProcessoModel) processoCombo.getSelectedItem();
        if (processo == null) throw new CampoVazioException("Processo");
        return processo;
    }

    // ===== GETTER — BOTAO =====
    public JButton getAddAudienciaBTN() {
        return addAudienciaBTN;
    }
}
