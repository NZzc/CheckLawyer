package View;

import javax.swing.*;
import java.awt.*;

public class TelaAddAudienciaView extends JFrame {

    private JButton addAudienciaBTN;

    private JTextField dataInput;
    private JTextField horaInput;
    private JTextField localInput;
    private JTextField descricaoInput;
    private JTextField idProcessoInput;

    public TelaAddAudienciaView() {

        setTitle("Adicionar Audiência");
        setSize(500, 420);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        Font tituloFont = new Font("Arial", Font.BOLD, 28);
        Font labelFont  = new Font("Arial", Font.BOLD, 14);
        Font inputFont  = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JLabel titulo = new JLabel("Adicionar Audiência");
        titulo.setFont(tituloFont);

        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        // ================= INPUTS =================
        dataInput       = new JTextField(18);
        horaInput       = new JTextField(18);
        localInput      = new JTextField(18);
        descricaoInput  = new JTextField(18);
        idProcessoInput = new JTextField(18);

        JTextField[] inputs = { dataInput, horaInput, localInput, descricaoInput, idProcessoInput };
        for (JTextField f : inputs) f.setFont(inputFont);

        // ================= FORM =================
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        int y = 0;
        y = addField(formPanel, gbc, y, "Data (DD/MM/AAAA):", dataInput, labelFont);
        y = addField(formPanel, gbc, y, "Hora (HH:MM):", horaInput, labelFont);
        y = addField(formPanel, gbc, y, "Local:", localInput, labelFont);
        y = addField(formPanel, gbc, y, "Descrição:", descricaoInput, labelFont);
        y = addField(formPanel, gbc, y, "ID Processo:", idProcessoInput, labelFont);

        // ================= BOTÃO =================
        addAudienciaBTN = new JButton("Adicionar Audiência");
        addAudienciaBTN.setFont(buttonFont);
        addAudienciaBTN.setPreferredSize(new Dimension(220, 50));

        JPanel painelBotao = new JPanel();
        painelBotao.add(addAudienciaBTN);

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

    private int addField(JPanel panel, GridBagConstraints gbc, int y,
                         String labelText, JTextField field, Font labelFont) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);

        gbc.gridx = 0; gbc.gridy = y;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);

        return y + 1;
    }

    // ================= GETTERS =================
    public JButton getAddAudienciaBTN()     { return addAudienciaBTN; }
    public JTextField getDataInput()         { return dataInput; }
    public JTextField getHoraInput()         { return horaInput; }
    public JTextField getLocalInput()        { return localInput; }
    public JTextField getDescricaoInput()    { return descricaoInput; }
    public JTextField getIdProcessoInput()   { return idProcessoInput; }
}
