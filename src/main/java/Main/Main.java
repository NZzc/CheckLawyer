package Main;

import Controller.TelaPrincipalController;
import Util.JPAUtil;

public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(JPAUtil::fechar));
        new TelaPrincipalController();
    }
}
