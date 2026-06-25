package Controller;

import View.*;

public class TelaPrincipalController {
    private TelaPrincipalView principalView;

    private TelaClienteController telaClienteController;

    private TelaFinanceiroController telaFinanceiroController;

    private TelaProcessoController telaProcessoController;

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
