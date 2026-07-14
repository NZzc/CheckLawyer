package View;

import Exception.SelecionarItemException;
import Model.AudienciaModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaAudienciaView extends JFrame {

    private JTable tabelaAudiencias;
    private JButton addAudienciaBTN;
    private JButton editarAudienciaBTN;
    private JButton excluirAudienciaBTN;

    public TelaAudienciaView() {
        setTitle("Audiências");
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

        JLabel titulo = new JLabel("Audiências");
        titulo.setFont(tituloFont);
        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        tabelaAudiencias = new JTable(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"ID", "Data", "Hora", "Local", "Tipo", "Descrição", "Resultado", "ID Processo"}));
        tabelaAudiencias.setFont(new Font("Arial", Font.PLAIN, 13));
        tabelaAudiencias.setRowHeight(22);

        JScrollPane scrollTabela = new JScrollPane(tabelaAudiencias);

        addAudienciaBTN = new JButton("Adicionar Audiência");
        editarAudienciaBTN = new JButton("Editar Audiência");
        excluirAudienciaBTN = new JButton("Excluir Audiência");
        addAudienciaBTN.setFont(buttonFont);
        editarAudienciaBTN.setFont(buttonFont);
        excluirAudienciaBTN.setFont(buttonFont);
        Dimension tamanhoBotaoAcao = new Dimension(210, 40);
        addAudienciaBTN.setPreferredSize(tamanhoBotaoAcao);
        editarAudienciaBTN.setPreferredSize(tamanhoBotaoAcao);
        excluirAudienciaBTN.setPreferredSize(tamanhoBotaoAcao);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painelBotoes.add(addAudienciaBTN);
        painelBotoes.add(editarAudienciaBTN);
        painelBotoes.add(excluirAudienciaBTN);

        JPanel main = new JPanel(new BorderLayout());
        main.add(painelTitulo, BorderLayout.NORTH);
        main.add(scrollTabela, BorderLayout.CENTER);
        main.add(painelBotoes, BorderLayout.SOUTH);
        setContentPane(main);
    }

    //=========================================================
    public void exibirErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro de validação", JOptionPane.ERROR_MESSAGE);
    }
    public void exibirSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void popularTabela(List<AudienciaModel> lista) {
        DefaultTableModel model = (DefaultTableModel) tabelaAudiencias.getModel();
        model.setRowCount(0);
        for (AudienciaModel a : lista) {
            model.addRow(new Object[]{a.getID(), a.getData(), a.getHora(), a.getLocal(), a.getTipo(), a.getDescricao(), a.getResultado(), a.getIdProcesso()});
        }
    }

    public int getIDlinhaSelecionada() throws SelecionarItemException {
        int linha = tabelaAudiencias.getSelectedRow();
        if (linha == -1) throw new SelecionarItemException("audiência");
        return Integer.parseInt(tabelaAudiencias.getValueAt(linha, 0).toString());
    }

    public boolean confirmarExclusao(String msg) {
        int ok = JOptionPane.showConfirmDialog(null, msg, "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        return ok == JOptionPane.YES_OPTION;
    }

    //=========================================================

    public JButton getAddAudienciaBTN() {
        return addAudienciaBTN;
    }

    public JButton getExcluirAudienciaBTN() {
        return excluirAudienciaBTN;
    }

    public JButton getEditarAudienciaBTN() {
        return editarAudienciaBTN;
    }
}
