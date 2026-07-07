package Controller;

import Dao.ClienteDAO;
import Model.ClienteModel;
import View.TelaClientesView;

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
            int ID = telaClientesView.getIDlinhaSelecionada();
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
        telaClientesView.popularTabela(lista);
    }

    public void exibirMensagem(String msg) {
        telaClientesView.exibirMensagem(msg);
    }


}