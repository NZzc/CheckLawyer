package View;

import Model.ProcessoModel;

import javax.swing.*;
import java.awt.*;

public class TelaAddAudienciaView extends JFrame {

    private JButton addAudienciaBTN;

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

        JLabel titulo = new JLabel("Adicionar Audiência");
        titulo.setFont(tituloFont);
        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

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

    // ===== GETTERS =====
    public JButton getAddAudienciaBTN() {
        return addAudienciaBTN;
    }

    public JTextField getDataInput() {
        return dataInput;
    }

    public JTextField getHoraInput() {
        return horaInput;
    }

    public JTextField getLocalInput() {
        return localInput;
    }

    public JComboBox<String> getTipoCombo() {
        return tipoCombo;
    }

    public JTextField getDescricaoInput() {
        return descricaoInput;
    }

    public JTextField getResultadoInput() {
        return resultadoInput;
    }

    public JComboBox<ProcessoModel> getProcessoCombo() {
        return processoCombo;
    }
}
