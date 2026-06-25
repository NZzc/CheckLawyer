package Controller;

import View.*;

public class TelaPrincipalController {
    private TelaPrincipalView principalView;

    private TelaClientesView telaClientesView;
    private TelaClienteController telaClienteController;

    private TelaFinanceiroView telaFinanceiroView;
    private TelaFinanceiroController telaFinanceiroController;

    private TelaProcessosView telaProcessosView;
    private TelaProcessoController telaProcessoController;

    private TelaAudienciaView telaAudienciaView;
    private TelaAudienciaController telaAudienciaController;


    public TelaPrincipalController() {
        principalView = new TelaPrincipalView();
        configurarEventos();
    }

    private void configurarEventos() {
        //tela clientes
        principalView.getClienteBTN().addActionListener(e -> {
            //permite instanciar apenas 1 tela por vez
            if (telaClienteController == null) {
                telaClienteController = new TelaClienteController();

                /*seta a variavel de controle para null, para poder abrir tela denovo depois
                telaClienteController.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        telaClienteController = null;
                    }
                });
            */

            }
        });

        //tela audiencia


        //tela processos


        //tela financeiro


    }


}
