package Controller;

import Dao.ClienteDAO;
import Model.ClienteModel;
import View.TelaClientesView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.List;

public class TelaClienteController {
    private ClienteDAO clienteDAO;
    private TelaClientesView telaClientesView;
    private List<ClienteModel> listaAtual;

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

            clienteDAO.excluir(ID);
            atualizarTabela();
        });

        telaClientesView.getOrdenarNomeBTN().addActionListener(e -> {
            Collections.sort(listaAtual);
            popularTabela(listaAtual);
        });

        telaClientesView.getOrdenarIdBTN().addActionListener(e -> {
            listaAtual.sort(ClienteModel.POR_ID);
            popularTabela(listaAtual);
        });

    }

    public void atualizarTabela() {
        listaAtual = clienteDAO.getLista();
        popularTabela(listaAtual);
    }

    private void popularTabela(List<ClienteModel> lista) {
        DefaultTableModel model =
                (DefaultTableModel) telaClientesView.getTabelaClientes().getModel();
        model.setRowCount(0);
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

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }


}