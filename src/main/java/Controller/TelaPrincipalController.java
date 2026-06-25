package Controller;

import View.*;

public class TelaPrincipalController {
    private TelaPrincipalView principalView;

    //private TelaClientesView telaClientesView;
    private TelaClienteController telaClienteController;

    //private TelaFinanceiroView telaFinanceiroView;
    private TelaFinanceiroController telaFinanceiroController;

    //private TelaProcessosView telaProcessosView;
    private TelaProcessoController telaProcessoController;

    //private TelaAudienciaView telaAudienciaView;
    private TelaAudienciaController telaAudienciaController;


    public TelaPrincipalController() {
        principalView = new TelaPrincipalView();
        configurarEventos();
    }

    private void configurarEventos() {
        //tela clientes
        principalView.getClienteBTN().addActionListener(e -> {
            new TelaClienteController();
        });

        //tela audiencia


        //tela processos


        //tela financeiro


    }


}
