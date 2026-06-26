package View;

import javax.swing.*;
import java.awt.*;

public class TelaAudienciaView extends JFrame {

    private JTable tabelaAudiencias;

    private JButton addAudienciaBTN;
    private JButton excluirAudienciaBTN;

    public TelaAudienciaView() {

        setTitle("Audiências");
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        // ================= FONTES =================
        Font tituloFont = new Font("Arial", Font.BOLD, 28);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // ================= TÍTULO =================
        JLabel titulo = new JLabel("Audiências");
        titulo.setFont(tituloFont);

        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        // ================= TABELA =================
        tabelaAudiencias = new JTable(
                new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                                "ID",
                                "Data",
                                "Hora",
                                "Local",
                                "Descrição",
                                "ID Processo"
                        }
                )
        );

        tabelaAudiencias.setFont(new Font("Arial", Font.PLAIN, 14));
        tabelaAudiencias.setRowHeight(22);

        JScrollPane scrollTabela = new JScrollPane(tabelaAudiencias);

        // ================= BOTÕES =================
        addAudienciaBTN = new JButton("Adicionar Audiência");
        excluirAudienciaBTN = new JButton("Excluir Audiência");

        addAudienciaBTN.setFont(buttonFont);
        excluirAudienciaBTN.setFont(buttonFont);

        addAudienciaBTN.setPreferredSize(new Dimension(210, 40));
        excluirAudienciaBTN.setPreferredSize(new Dimension(210, 40));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.add(excluirAudienciaBTN);
        painelBotoes.add(addAudienciaBTN);

        // ================= LAYOUT PRINCIPAL =================
        JPanel main = new JPanel(new BorderLayout());

        main.add(painelTitulo, BorderLayout.NORTH);
        main.add(scrollTabela, BorderLayout.CENTER);
        main.add(painelBotoes, BorderLayout.SOUTH);

        setContentPane(main);
    }

    // ================= GETTERS =================
    public JButton getAddAudienciaBTN() {
        return addAudienciaBTN;
    }

    public JButton getExcluirAudienciaBTN() {
        return excluirAudienciaBTN;
    }

    public JTable getTabelaAudiencias() {
        return tabelaAudiencias;
    }
}
