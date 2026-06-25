package Controller;

import View.TelaAddClienteView;
import View.TelaClientesView;

public class TelaClienteController {

    private TelaClientesView telaClientesView;

    private TelaAddClienteView telaAddClienteView;
    private TelaAddClienteController telaAddClienteController;

    public TelaClienteController() {
        telaClientesView = new TelaClientesView();

        configurarEventos();
    }

    private void configurarEventos() {

        telaClientesView.getAddClienteBTN().addActionListener(e -> {
            new TelaAddClienteController();
        });
    }
}