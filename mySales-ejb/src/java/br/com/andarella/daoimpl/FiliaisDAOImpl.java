package br.com.andarella.daoimpl;

import br.com.andarella.dao.FiliaisDAO;
import br.com.andarella.util.AbstractDAO;
import br.com.domain.Filiais;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FiliaisDAOImpl extends AbstractDAO<Filiais> implements FiliaisDAO{

    @PersistenceContext
    private final EntityManager em;
    
    public FiliaisDAOImpl() {
        super(Filiais.class);
        em = getEntityManager();
    }

    @Override
    public List<Filiais> getListarFiliais() {
        Query query = em.createNamedQuery("Filiais.findAll");
        return query.getResultList();
    }
    
}
