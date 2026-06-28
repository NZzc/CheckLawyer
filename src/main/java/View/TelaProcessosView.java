package View;

import javax.swing.*;
import java.awt.*;

public class TelaProcessosView extends JFrame {

    private JTable tabelaProcessos;
    private JButton addProcessoBTN;
    private JButton excluirProcessoBTN;

    public TelaProcessosView() {
        setTitle("Processos");
        setSize(1050, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        Font tituloFont = new Font("Arial", Font.BOLD, 28);
        Font buttonFont = new Font("Arial", Font.BOLD, 15);

        JLabel titulo = new JLabel("Processos");
        titulo.setFont(tituloFont);
        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        tabelaProcessos = new JTable(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"ID", "Número", "Área", "Vara / Tribunal", "Descrição", "Status", "Data Abertura", "ID Cliente"}));
        tabelaProcessos.setFont(new Font("Arial", Font.PLAIN, 13));
        tabelaProcessos.setRowHeight(22);

        JScrollPane scrollTabela = new JScrollPane(tabelaProcessos);

        addProcessoBTN = new JButton("Adicionar Processo");
        excluirProcessoBTN = new JButton("Excluir Processo");
        addProcessoBTN.setFont(buttonFont);
        excluirProcessoBTN.setFont(buttonFont);
        addProcessoBTN.setPreferredSize(new Dimension(200, 40));
        excluirProcessoBTN.setPreferredSize(new Dimension(200, 40));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.add(excluirProcessoBTN);
        painelBotoes.add(addProcessoBTN);

        JPanel main = new JPanel(new BorderLayout());
        main.add(painelTitulo, BorderLayout.NORTH);
        main.add(scrollTabela, BorderLayout.CENTER);
        main.add(painelBotoes, BorderLayout.SOUTH);
        setContentPane(main);
    }

    public JButton getAddProcessoBTN() {
        return addProcessoBTN;
    }

    public JButton getExcluirProcessoBTN() {
        return excluirProcessoBTN;
    }

    public JTable getTabelaProcessos() {
        return tabelaProcessos;
    }
}
