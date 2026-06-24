package View;

import javax.swing.*;
import java.awt.*;

public class TelaClientesView extends JFrame {

    private JTable tabelaClientes;

    private JButton addClienteBTN;
    private JButton excluirClienteBTN;

    public TelaClientesView() {

        setTitle("Clientes");
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
        JLabel titulo = new JLabel("Clientes");
        titulo.setFont(tituloFont);

        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        // ================= TABELA =================
        tabelaClientes = new JTable(
                new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                                "ID", "Nome", "CPF/CNPJ", "Telefone", "Email"
                        }
                )
        );

        tabelaClientes.setFont(new Font("Arial", Font.PLAIN, 14));
        tabelaClientes.setRowHeight(22);

        JScrollPane scrollTabela = new JScrollPane(tabelaClientes);

        // ================= BOTÕES =================
        addClienteBTN = new JButton("Adicionar Cliente");
        excluirClienteBTN = new JButton("Excluir Cliente");

        addClienteBTN.setFont(buttonFont);
        excluirClienteBTN.setFont(buttonFont);

        addClienteBTN.setPreferredSize(new Dimension(180, 40));
        excluirClienteBTN.setPreferredSize(new Dimension(180, 40));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.add(excluirClienteBTN);
        painelBotoes.add(addClienteBTN);

        // ================= LAYOUT PRINCIPAL =================
        JPanel main = new JPanel(new BorderLayout());

        main.add(painelTitulo, BorderLayout.NORTH);
        main.add(scrollTabela, BorderLayout.CENTER);
        main.add(painelBotoes, BorderLayout.SOUTH);

        setContentPane(main);
    }

    // ================= GETTERS =================
    public JButton getAddClienteBTN() {
        return addClienteBTN;
    }

    public JButton getExcluirClienteBTN() {
        return excluirClienteBTN;
    }

    public JTable getTabelaClientes() {
        return tabelaClientes;
    }
}