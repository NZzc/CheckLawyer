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

        // tela clientes
        principalView.getClienteBTN().addActionListener(e -> {
            new TelaClienteController();
        });

        // tela audiencia
        principalView.getAudicenciaBTN().addActionListener(e -> {
            new TelaAudienciaController();
        });

        // tela processos
        principalView.getProcessosBTN().addActionListener(e -> {
            new TelaProcessoController();
        });

        // tela financeiro
        principalView.getFinanceiroBTN().addActionListener(e -> {
            new TelaFinanceiroController();
        });
    }
}
