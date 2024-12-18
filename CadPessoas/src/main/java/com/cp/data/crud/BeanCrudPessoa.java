
package com.cp.data.crud;

import com.cp.data.crud.interfaces.EMNames;
import com.cp.data.crud.interfaces.AbstractCrud;
import com.cp.data.model.Pessoa;
import com.cp.util.AppLog;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

/**
 *
 * @author utfpr
 */
@Stateless
public class BeanCrudPessoa extends AbstractCrud<Pessoa> {

    // TODO: Implementar o gerenciamento de conexão e transações automaticamente utilizando EJB
    @PersistenceConext
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

    public BeanCrudPessoa() {
        super(Pessoa.class);
    }


}
