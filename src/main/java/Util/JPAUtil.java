package Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Singleton para o EntityManagerFactory.
 * A factory deve ser criada uma única vez por aplicação.
 * Cada DAO obtém seu próprio EntityManager (leve, criado por operação).
 */
public class JPAUtil {

    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("CheckLawyerPU");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(
                    "Falha ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /** Chamar apenas ao encerrar a aplicação. */
    public static void fechar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
