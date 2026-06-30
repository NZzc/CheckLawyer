package Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("CheckLawyerDAO");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(
                    "Falha ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //Chama ao encerrar a aplicação.
    public static void fechar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
