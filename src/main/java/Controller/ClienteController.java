package Controller;

import View.TelaAddClienteView;
import View.TelaClientesView;

public class ClienteController {
    private TelaClientesView telaClientesView;
    private TelaAddClienteView telaAddClienteView;


    public ClienteController() {
        configurarEventos();
    }

    private void configurarEventos() {
        telaClientesView.getAddClienteBTN().addActionListener(e -> {
            if (telaAddClienteView == null) {
                telaAddClienteView = new TelaAddClienteView();

                telaAddClienteView.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        telaAddClienteView = null;
                    }
                });
            }
        });


    }
}
