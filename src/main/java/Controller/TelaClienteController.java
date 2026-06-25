package Controller;

import Dao.ClienteDAO;
import Model.EnderecoModel;
import View.TelaAddClienteView;
import View.TelaClientesView;

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
            new TelaAddClienteController();
        });
    }

    public void atualizarTabela(){

    }


}