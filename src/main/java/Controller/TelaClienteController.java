package Controller;

import Dao.ClienteDAO;
import Exception.SelecionarItemException;
import Model.ClienteModel;
import View.TelaClientesView;

import java.util.Collections;
import java.util.List;

public class TelaClienteController {
    private final ClienteDAO clienteDAO;
    private final TelaClientesView telaClientesView;
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

        telaClientesView.getEditarClienteBTN().addActionListener(e -> {
            try {
                int ID = telaClientesView.getIDlinhaSelecionada();
                ClienteModel cliente = clienteDAO.buscarPorId(ID);

                if (cliente == null) {
                    exibirMensagem("Cliente não encontrado.");
                    return;
                }

                new TelaEditClienteController(this, cliente);

            } catch (SelecionarItemException ex) {
                exibirMensagem(ex.getMessage());
            } catch (Exception ex) {
                exibirMensagem("Erro inesperado: " + ex.getMessage());
            }
        });

        telaClientesView.getExcluirClienteBTN().addActionListener(e -> {
            try {
                int ID = telaClientesView.getIDlinhaSelecionada();
                clienteDAO.excluir(ID);
                atualizarTabela();
            } catch (SelecionarItemException ex) {
                exibirMensagem(ex.getMessage());
            } catch (Exception ex) {
                exibirMensagem("Erro inesperado: " + ex.getMessage());
            }
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
        telaClientesView.popularTabela(lista);
    }

    public void exibirMensagem(String msg) {
        telaClientesView.exibirMensagem(msg);
    }


}
