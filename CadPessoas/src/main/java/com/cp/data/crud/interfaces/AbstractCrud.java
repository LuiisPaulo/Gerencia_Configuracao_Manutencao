/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cp.data.crud.interfaces;

import java.util.Collections;
import java.util.List;
import com.cp.util.AppLog;

import jakarta.persistence.EntityManager;

/**
 *
 * @author utfpr
 */
public abstract class AbstractCrud<T> {
    
    private Class<T> entityClass;

    // TODO: Alteração de classe
    protected AbstractCrud(Class<T> entityClass) {
        this.entityClass = entityClass;
    }



    protected abstract EntityManager getEntityManager(){
        return entityManager;
    }


    protected abstract  void close();



    public Exception persist(T entity) {
        try {
            getEntityManager().persist(entity);
            getEntityManager().flush();
            AppLog.getInstance().info("Registro realizado com sucesso na classe: " + this..getClass().getName());
            return null;
        } catch (Exception e) {
            AppLog.getInstance().warn("Erro ao inserir no banco de dados: " + this.getClass().getName() + "==>" + e.getMessage());
            return e;
        }
    }

    public Exception merge(T entity) {
        try {
            getEntityManager().merge(entity); 
            getEntityManager().flush();
            AppLog.getInstance().info("Registro alterado com sucesso pela classe: " + this.getClass().getName());
            return null;
        } catch (Exception e) {
            AppLog.getInstance().warn("Erro ao alterar o registro: " + this.getClass().getName() + "==>" + e.getMessage());
            return e;
        }
    }

    public Exception remove(T entity) {
        try {
            getEntityManager().remove(getEntityManager().merge(entity)); 
            getEntityManager().flush();
            AppLog.getInstance().info("Registro removido com sucesso pela classe: " + this.getClass().getName());
            return null;
        } catch (Exception e) {
            AppLog.getInstance().warn("Erro ao remover o registro: " + this.getClass().getName() + "==>" + e.getMessage());
            return e;
        }
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> getAll() {
        try {
            jakarta.persistence.criteria.CriteriaQuery cq;
            cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return getEntityManager().createQuery(cq).getResultList();
        } catch (Exception e) {
            AppLog.getInstance().warn("Erro ao recuperar os registros: " + this.getClass().getName() + "==>" + e.getMessage());
            return Collections.emptyList();
        }

    }

    public List<T> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

// teste
}
