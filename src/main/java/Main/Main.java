package Main;

import Controller.TelaPrincipalController;
import Util.JPAUtil;

public class Main {
    public static void main(String[] args) {
        // Fecha o EntityManagerFactory ao encerrar a aplicação
        Runtime.getRuntime().addShutdownHook(new Thread(JPAUtil::fechar));
        new TelaPrincipalController();
    }
}
