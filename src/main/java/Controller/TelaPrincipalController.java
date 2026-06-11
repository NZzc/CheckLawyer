package Controller;

import View.TelaClientesView;
import View.TelaPrincipalView;

public class TelaPrincipalController{
    private TelaPrincipalView view;

    public TelaPrincipalController(TelaPrincipalView view) {
        this.view = view;

        configurarEventos();
    }

    private void configurarEventos() {

        // Eventos dos botões
        abrirTelaClientes();
    }

    private void abrirTelaClientes() {

        view.getClienteBTN().addActionListener(e -> {

            TelaClientesView clienteView = new TelaClientesView();

            //new ClienteController(clienteView);

            clienteView.setVisible(true);
        });
    }

}
