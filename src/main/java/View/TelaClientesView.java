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
    private JButton editarClienteBTN;
    private JButton excluirClienteBTN;
    private JButton ordenarNomeBTN;
    private JButton ordenarIdBTN;

    public TelaClientesView() {

        setTitle("Clientes");
        setSize(1100, 600);
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
        editarClienteBTN = new JButton("Editar Cliente");
        excluirClienteBTN = new JButton("Excluir Cliente");
        ordenarNomeBTN = new JButton("Ordenar por Nome");
        ordenarIdBTN = new JButton("Ordenar por ID");

        addClienteBTN.setFont(buttonFont);
        editarClienteBTN.setFont(buttonFont);
        excluirClienteBTN.setFont(buttonFont);
        ordenarNomeBTN.setFont(buttonFont);
        ordenarIdBTN.setFont(buttonFont);

        Dimension tamanhoBotaoAcao = new Dimension(180, 40);
        addClienteBTN.setPreferredSize(tamanhoBotaoAcao);
        editarClienteBTN.setPreferredSize(tamanhoBotaoAcao);
        excluirClienteBTN.setPreferredSize(tamanhoBotaoAcao);

        JPanel painelOrdenacao = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelOrdenacao.add(ordenarNomeBTN);
        painelOrdenacao.add(ordenarIdBTN);

        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelAcoes.add(addClienteBTN);
        painelAcoes.add(editarClienteBTN);
        painelAcoes.add(excluirClienteBTN);

        JPanel painelBotoes = new JPanel(new BorderLayout());
        painelBotoes.add(painelOrdenacao, BorderLayout.WEST);
        painelBotoes.add(painelAcoes, BorderLayout.EAST);

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

    public JButton getEditarClienteBTN() {
        return editarClienteBTN;
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
