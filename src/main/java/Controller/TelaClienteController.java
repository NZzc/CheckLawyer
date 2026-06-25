package Controller;

import Dao.ClienteDAO;
import Model.ClienteModel;
import Model.EnderecoModel;
import View.TelaAddClienteView;
import View.TelaClientesView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaClienteController {
    private ClienteDAO clienteDAO;
    private TelaClientesView telaClientesView;

    private TelaAddClienteView telaAddClienteView;
    private TelaAddClienteController telaAddClienteController;

    public TelaClienteController() {
        telaClientesView = new TelaClientesView();
        clienteDAO = new ClienteDAO();

        configurarEventos();
    }

    private void configurarEventos() {

        telaClientesView.getAddClienteBTN().addActionListener(e -> {
            new TelaAddClienteController(this);
        });

        telaClientesView.getExcluirClienteBTN().addActionListener(e -> {
            int linha = telaClientesView.getTabelaClientes().getSelectedRow();

            if (linha == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um cliente!");
                return;
            }

            String IDstr = telaClientesView.getTabelaClientes().getValueAt(linha, 0).toString();
            int ID =  Integer.parseInt(IDstr);

            clienteDAO.excluirCliente(ID);
            atualizarTabela();
        });
    }

    public void atualizarTabela(){


        DefaultTableModel model = (DefaultTableModel) telaClientesView.getTabelaClientes().getModel();

        model.setRowCount(0); // limpa

        for (ClienteModel cliente : clienteDAO.getListaClientes()) {
            model.addRow(new Object[]{
                    cliente.getID(),
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getTelefone(),
                    cliente.getEmail(),
                    cliente.getEndereco().getRua(),
                    cliente.getEndereco().getNumero(),
                    cliente.getEndereco().getBairro(),
                    cliente.getEndereco().getCidade(),
                    cliente.getEndereco().getUf(),
                    cliente.getEndereco().getCep()
            });
        }

    }


}