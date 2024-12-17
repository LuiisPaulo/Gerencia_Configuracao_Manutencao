
package com.cp.data.crud;

import com.cp.data.crud.interfaces.EMNames;
import com.cp.data.crud.interfaces.AbstractCrud;
import com.cp.data.model.Cidade;
import com.cp.util.AppLog;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

/**
 *
 * @author utfpr
 */
@Stateless
public class BeanCrudCidade extends AbstractCrud<Cidade> {

    // TODO: Implementar o gerenciamento de conexão e transações utilizando EJB
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
      return em;
    }

    @Override
    protected void close() {
            if(em!=null){
                getEntityManager().close();
            }
    }


    public BeanCrudCidade() {
        super(Cidade.class);
    }

}
