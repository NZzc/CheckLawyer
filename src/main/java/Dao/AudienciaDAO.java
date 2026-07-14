package Dao;

import Model.AudienciaModel;
import Util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AudienciaDAO implements PersistivelInterface <AudienciaModel> {

    public void inserir(AudienciaModel audiencia) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(audiencia);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            AudienciaModel audiencia = em.find(AudienciaModel.class, id);
            if (audiencia != null) em.remove(audiencia);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<AudienciaModel> getLista() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT a FROM AudienciaModel a JOIN FETCH a.processo ORDER BY a.data, a.hora",
                    AudienciaModel.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void editar(AudienciaModel entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public AudienciaModel buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<AudienciaModel> audiencias = em.createQuery(
                    "SELECT a FROM AudienciaModel a JOIN FETCH a.processo WHERE a.ID = :id",
                    AudienciaModel.class
            ).setParameter("id", id).getResultList();
            return audiencias.isEmpty() ? null : audiencias.get(0);
        } finally {
            em.close();
        }
    }
}
