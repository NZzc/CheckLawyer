package View;

import Model.ClienteModel;
import Exception.SelecionarItemException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaClientesView extends JFrame {

    private JTable tabelaClientes;

    private JButton addClienteBTN;
    private JButton excluirClienteBTN;
    private JButton ordenarNomeBTN;
    private JButton ordenarIdBTN;

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
                                "ID",
                                "Nome / Empresa",
                                "CPF/CNPJ",
                                "Telefone",
                                "Email",
                                "Observacao",
                                "Rua",
                                "Número",
                                "Bairro",
                                "Cidade",
                                "UF",
                                "CEP"
                        }
                )
        );

        tabelaClientes.setFont(new Font("Arial", Font.PLAIN, 14));
        tabelaClientes.setRowHeight(22);

        JScrollPane scrollTabela = new JScrollPane(tabelaClientes);

        // ================= BOTÕES =================
        addClienteBTN = new JButton("Adicionar Cliente");
        excluirClienteBTN = new JButton("Excluir Cliente");
        ordenarNomeBTN = new JButton("Ordenar por Nome");
        ordenarIdBTN = new JButton("Ordenar por ID");

        addClienteBTN.setFont(buttonFont);
        excluirClienteBTN.setFont(buttonFont);
        ordenarNomeBTN.setFont(buttonFont);
        ordenarIdBTN.setFont(buttonFont);

        addClienteBTN.setPreferredSize(new Dimension(180, 40));
        excluirClienteBTN.setPreferredSize(new Dimension(180, 40));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.add(excluirClienteBTN);
        painelBotoes.add(addClienteBTN);
        painelBotoes.add(ordenarNomeBTN);
        painelBotoes.add(ordenarIdBTN);

        // ================= LAYOUT PRINCIPAL =================
        JPanel main = new JPanel(new BorderLayout());

        main.add(painelTitulo, BorderLayout.NORTH);
        main.add(scrollTabela, BorderLayout.CENTER);
        main.add(painelBotoes, BorderLayout.SOUTH);

        setContentPane(main);
    }

    //=========================================================
    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void popularTabela(List<ClienteModel> lista) {
        DefaultTableModel model = (DefaultTableModel) getTabelaClientes().getModel();

        model.setRowCount(0); //reseta tabela

        for (ClienteModel cliente : lista) {
            model.addRow(new Object[]{
                    cliente.getID(),
                    cliente.getNome(),
                    cliente.getDocumento(),
                    cliente.getTelefone(),
                    cliente.getEmail(),
                    cliente.getObservacao(),
                    cliente.getEndereco().getRua(),
                    cliente.getEndereco().getNumero(),
                    cliente.getEndereco().getBairro(),
                    cliente.getEndereco().getCidade(),
                    cliente.getEndereco().getUf(),
                    cliente.getEndereco().getCep()
            });
        }
    }


    public int getIDlinhaSelecionada() throws SelecionarItemException {
        int linha = getTabelaClientes().getSelectedRow();
        if (linha == -1) throw new SelecionarItemException("cliente");

        String IDstr = getTabelaClientes().getValueAt(linha, 0).toString();
        return Integer.parseInt(IDstr);
    }
    //=========================================================


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

    public JButton getOrdenarNomeBTN() {
        return ordenarNomeBTN;
    }

    public JButton getOrdenarIdBTN() {
        return ordenarIdBTN;
    }
}