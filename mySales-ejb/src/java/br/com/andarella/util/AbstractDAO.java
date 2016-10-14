package br.com.andarella.util;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

public class AbstractDAO<T> {
	private Class<T> entidade;
	
	@PersistenceContext(unitName = "mySales-ejbPU")
	private EntityManager em;
	
	protected EntityManager getEntityManager(){
		return this.em;
	}
	
	public AbstractDAO(Class<T> entidade) {
        this.entidade = entidade;
        }
	
	public void cadastrar(T entidade){
		getEntityManager().persist(entidade);
	}
	
	public void alterar(T entidade){
		getEntityManager().merge(entidade);
	}
	
	public void excluir(T entidade){
		getEntityManager().remove(getEntityManager().merge(entidade));
	}
	
	public T obterPorId(Integer id){
		return getEntityManager().find(entidade, id);
	}
	
	public List<T> listar(){
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entidade));
        return getEntityManager().createQuery(cq).getResultList();
    }
	


}
