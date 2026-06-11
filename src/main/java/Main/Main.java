package Main;

import Controller.TelaPrincipalController;
import View.TelaPrincipalView;

public class Main {
    public static void main(String[] args) {

        TelaPrincipalView view = new TelaPrincipalView();

        new TelaPrincipalController(view);

        view.setVisible(true);
    }
}
