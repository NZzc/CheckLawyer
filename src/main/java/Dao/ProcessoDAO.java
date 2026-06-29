package Dao;

import Model.ProcessoModel;
import Util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProcessoDAO {

    public void addProcesso(ProcessoModel processo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            // merge garante que o ClienteModel (já persistido) seja gerenciado
            em.merge(processo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void excluirProcesso(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            ProcessoModel p = em.find(ProcessoModel.class, id);
            if (p != null) em.remove(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<ProcessoModel> getListaProcessos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JOIN FETCH carrega o cliente junto para evitar LazyInitializationException
            return em.createQuery("SELECT p FROM ProcessoModel p JOIN FETCH p.cliente ORDER BY p.numero", ProcessoModel.class).getResultList();
        } finally {
            em.close();
        }
    }

    public boolean verificaNumeroRepetido(String numero) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(p) FROM ProcessoModel p WHERE p.numero = :numero", Long.class).setParameter("numero", numero).getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public ProcessoModel buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ProcessoModel.class, id);
        } finally {
            em.close();
        }
    }
}
