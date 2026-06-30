package Dao;

import Model.ClienteModel;
import Util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDAO implements PersistivelInterface <ClienteModel> {

    public void inserir(ClienteModel cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            ClienteModel c = em.find(ClienteModel.class, id);
            if (c != null) em.remove(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<ClienteModel> getLista() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM ClienteModel c ORDER BY c.nome", ClienteModel.class).getResultList();
        } finally {
            em.close();
        }
    }

    public boolean verificaCpfCnpjRepetido(String cpfCnpj) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Verifica CPF em PF e CNPJ em PJ na mesma tabela
            Long count = em.createQuery(
                       "SELECT COUNT(c) FROM ClienteModel c "
                            + "WHERE (TYPE(c) = Model.ClienteFisicoModel   AND TREAT(c AS Model.ClienteFisicoModel).cpf   = :doc) "
                            + "   OR (TYPE(c) = Model.ClienteJuridicoModel AND TREAT(c AS Model.ClienteJuridicoModel).cnpj = :doc)"
                    , Long.class).setParameter("doc", cpfCnpj).getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public ClienteModel buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ClienteModel.class, id);
        } finally {
            em.close();
        }
    }
}
