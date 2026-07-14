package View;

import Exception.SelecionarItemException;
import Model.ProcessoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaProcessosView extends JFrame {

    private JTable tabelaProcessos;
    private JButton addProcessoBTN;
    private JButton editarProcessoBTN;
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
        editarProcessoBTN = new JButton("Editar Processo");
        excluirProcessoBTN = new JButton("Excluir Processo");
        addProcessoBTN.setFont(buttonFont);
        editarProcessoBTN.setFont(buttonFont);
        excluirProcessoBTN.setFont(buttonFont);
        Dimension tamanhoBotaoAcao = new Dimension(200, 40);
        addProcessoBTN.setPreferredSize(tamanhoBotaoAcao);
        editarProcessoBTN.setPreferredSize(tamanhoBotaoAcao);
        excluirProcessoBTN.setPreferredSize(tamanhoBotaoAcao);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.add(addProcessoBTN);
        painelBotoes.add(editarProcessoBTN);
        painelBotoes.add(excluirProcessoBTN);

        JPanel main = new JPanel(new BorderLayout());
        main.add(painelTitulo, BorderLayout.NORTH);
        main.add(scrollTabela, BorderLayout.CENTER);
        main.add(painelBotoes, BorderLayout.SOUTH);
        setContentPane(main);
    }

    public void popularTabela(List<ProcessoModel> lista) {
        DefaultTableModel model = (DefaultTableModel) tabelaProcessos.getModel();
        model.setRowCount(0);
        for (ProcessoModel p : lista) {
            model.addRow(new Object[]{p.getID(), p.getNumero(), p.getArea(), p.getVara(), p.getDescricao(), p.getStatus(), p.getDataAbertura(), p.getIdCliente()});
        }
    }

    public int getIDlinhaSelecionada() throws SelecionarItemException {
        int linha = tabelaProcessos.getSelectedRow();
        if (linha == -1) throw new SelecionarItemException("processo");
        return Integer.parseInt(tabelaProcessos.getValueAt(linha, 0).toString());
    }

    public boolean confirmarExclusao(String msg) {
        int ok = JOptionPane.showConfirmDialog(null, msg, "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        return ok == JOptionPane.YES_OPTION;
    }

    public void exibirErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public JButton getAddProcessoBTN() {
        return addProcessoBTN;
    }

    public JButton getExcluirProcessoBTN() {
        return excluirProcessoBTN;
    }

    public JButton getEditarProcessoBTN() {
        return editarProcessoBTN;
    }
}
