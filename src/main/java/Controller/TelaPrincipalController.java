package Controller;

import View.*;

public class TelaPrincipalController {
    private TelaPrincipalView principalView;
    private TelaClientesView telaClientesView;
    private TelaFinanceiroView telaFinanceiroView;
    private TelaProcessosView telaProcessosView;
    private TelaAudienciaView telaAudienciaView;


    public TelaPrincipalController() {
        principalView = new TelaPrincipalView();
        configurarEventos();
    }

    private void configurarEventos() {
        //tela clientes
        principalView.getClienteBTN().addActionListener(e -> {
            //permite instanciar apenas 1 tela por vez
            if (telaClientesView == null) {
                telaClientesView = new TelaClientesView();

                //seta a variavel de controle para null, para poder abrir tela denovo depois
                telaClientesView.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        telaClientesView = null;
                    }
                });
            }
        });

        //tela audiencia


        //tela processos


        //tela financeiro


    }


}
