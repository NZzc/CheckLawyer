package Controller;

import Dao.ClienteDAO;
import Model.ClienteModel;
import View.TelaAddClienteView;
import View.TelaClientesView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaClienteController {
    private ClienteDAO clienteDAO;
    private TelaClientesView telaClientesView;

    public TelaClienteController() {
        telaClientesView = new TelaClientesView();
        clienteDAO = new ClienteDAO();

        atualizarTabela();

        configurarEventos();
    }

    private void configurarEventos() {

        telaClientesView.getAddClienteBTN().addActionListener(e -> {
            new TelaAddClienteController(this);
        });

        telaClientesView.getExcluirClienteBTN().addActionListener(e -> {
            int linha = telaClientesView.getTabelaClientes().getSelectedRow();

            if (linha == -1) {
                exibirMensagem("Selecione um cliente!");
                return;
            }

            String IDstr = telaClientesView.getTabelaClientes().getValueAt(linha, 0).toString();
            int ID = Integer.parseInt(IDstr);

            clienteDAO.excluirCliente(ID);
            atualizarTabela();
        });
    }

    public void atualizarTabela() {


        DefaultTableModel model = (DefaultTableModel) telaClientesView.getTabelaClientes().getModel();

        model.setRowCount(0); // limpa

        for (ClienteModel cliente : clienteDAO.getListaClientes()) {
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

    public void exibirMensagem(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }


}