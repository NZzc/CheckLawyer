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

            if (telaAddClienteController == null) {

                telaAddClienteController = new TelaAddClienteController();

                /*
                telaAddClienteController.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        telaAddClienteView = null;
                    }
                });

                 */
            }
        });
    }
}