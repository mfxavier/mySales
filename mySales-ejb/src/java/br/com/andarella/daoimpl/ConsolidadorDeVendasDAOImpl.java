
package br.com.andarella.daoimpl;

import br.com.andarella.dao.ConsolidadorDeVendasDAO;
import javax.ejb.Stateless;
import br.com.andarella.util.AbstractDAO;
import br.com.domain.ConsolidadorDeVendas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ConsolidadorDeVendasDAOImpl extends AbstractDAO<ConsolidadorDeVendas> implements ConsolidadorDeVendasDAO{

    @PersistenceContext
    private final EntityManager em;

    public ConsolidadorDeVendasDAOImpl() {
        super(ConsolidadorDeVendas.class);
         em = getEntityManager();
    }

    @Override
    public List<ConsolidadorDeVendas> getListarConsolidadorDeVendas(String listaDeFiliais,String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        
       String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'1'";
       Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       query.setParameter(1, listaDeFiliais);
       query.setParameter(2, dataVendaIniSelecionada);
       query.setParameter(3, dataVendaFimSelecionada);
        
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        
    }
    

    @Override
    public List<ConsolidadorDeVendas> getListarConsolidadorDeVendasStart(String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        
       String sql = "exec [sProc_CallConsolidadorDeVendas] '''14 - BARRA SHOPPING''',?,?,'1'";
       Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       
       query.setParameter(1, dataVendaIniSelecionada);
       query.setParameter(2, dataVendaFimSelecionada);
        
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getGraficoDePercentualdeVendasStart(String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendas] '''14 - BARRA SHOPPING''',?,?,'2'";
       Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       
       query.setParameter(1, dataVendaIniSelecionada);
       query.setParameter(2, dataVendaFimSelecionada);
        
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getGraficoDePercentualdeVendas(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
       String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'2'";
       Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       query.setParameter(1, listaDeFiliais);
       query.setParameter(2, dataVendaIniSelecionada);
       query.setParameter(3, dataVendaFimSelecionada);
        
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getListarMovimentacoesDiariasStart(String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendas] '''14 - BARRA SHOPPING''',?,?,'3'";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       
        query.setParameter(1, dataVendaIniSelecionada);
        query.setParameter(2, dataVendaFimSelecionada);
        
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getListarMovimentacoesDiarias(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
         String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'3'";
       Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       query.setParameter(1, listaDeFiliais);
       query.setParameter(2, dataVendaIniSelecionada);
       query.setParameter(3, dataVendaFimSelecionada);
        
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getGraficoPercentualPorLinhaProdutoStart(String dataVendaIniSelecionada, 
                                                                               String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendas] '''14 - BARRA SHOPPING''',?,?,'4'";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       
        query.setParameter(1, dataVendaIniSelecionada);
        query.setParameter(2, dataVendaFimSelecionada);
        
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getGraficoPercentualPorLinhaProduto(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
       String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'4'";
       Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       query.setParameter(1, listaDeFiliais);
       query.setParameter(2, dataVendaIniSelecionada);
       query.setParameter(3, dataVendaFimSelecionada);
        
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getListarMovimentacoesPorGrupoProdutoStart(String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
      
        String sql = "exec [sProc_CallConsolidadorDeVendas] '''14 - BARRA SHOPPING''',?,?,'5'";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       
        query.setParameter(1, dataVendaIniSelecionada);
        query.setParameter(2, dataVendaFimSelecionada);
        
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getListarMovimentacoesPorGrupoProduto(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
      
        String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'5'";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, listaDeFiliais);
        query.setParameter(2, dataVendaIniSelecionada);
        query.setParameter(3, dataVendaFimSelecionada);
        
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getTotaisPorGrupoProdutoStart(String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendas] '''14 - BARRA SHOPPING''',?,?,'6'";
       Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       query.setParameter(1, dataVendaIniSelecionada);
       query.setParameter(2, dataVendaFimSelecionada);
    
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getTotaisPorGrupoProduto(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada, String vendedorApelido) {
        String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'6',?";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, listaDeFiliais);
        query.setParameter(2, dataVendaIniSelecionada);
        query.setParameter(3, dataVendaFimSelecionada);
        query.setParameter(4, vendedorApelido); 
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getListarVendedoresStart(String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
       String sql = "exec [sProc_CallConsolidadorDeVendas] '''14 - BARRA SHOPPING''',?,?,'7'";
       Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
       query.setParameter(1, dataVendaIniSelecionada);
       query.setParameter(2, dataVendaFimSelecionada);
    
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getListarVendedores(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'7',?";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, listaDeFiliais);
        query.setParameter(2, dataVendaIniSelecionada);
        query.setParameter(3, dataVendaFimSelecionada);
        
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getListarMovimentacoesDiariasPorFilial(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'31',?";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, listaDeFiliais);
        query.setParameter(2, dataVendaIniSelecionada);
        query.setParameter(3, dataVendaFimSelecionada);
        
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getListarTotaisMovimentacoesDiariasPorFilial(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'32',?";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, listaDeFiliais);
        query.setParameter(2, dataVendaIniSelecionada);
        query.setParameter(3, dataVendaFimSelecionada);
        
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getTotaisPorVendedoresCategorias(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada, String vendedor) {
         String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'8',?";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, listaDeFiliais);
        query.setParameter(2, dataVendaIniSelecionada);
        query.setParameter(3, dataVendaFimSelecionada);
        query.setParameter(4, vendedor); 
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

   
    @Override
    public List<ConsolidadorDeVendas> getGraficoGradeMedia(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        
        String sql = "exec [sProc_CallConsolidadorDeVendas] ?,?,?,'9'";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, listaDeFiliais);
        query.setParameter(2, dataVendaIniSelecionada);
        query.setParameter(3, dataVendaFimSelecionada);
       
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getGraficoGradeMediaStart(String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendas] '''14 - BARRA SHOPPING''',?,?,'9'";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, dataVendaIniSelecionada);
        query.setParameter(2, dataVendaFimSelecionada);


        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    } 

    @Override
    public List<ConsolidadorDeVendas> getVendasPorCategoriasStart(String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendasPorCategorias2] '''14 - BARRA SHOPPING''',?,?";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, dataVendaIniSelecionada);
        query.setParameter(2, dataVendaFimSelecionada);
        
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ConsolidadorDeVendas> getVendasPorCategorias(String listaDeFiliais, String dataVendaIniSelecionada, String dataVendaFimSelecionada) {
        String sql = "exec [sProc_CallConsolidadorDeVendasPorCategorias2] ?,?,?";
        Query query = em.createNativeQuery(sql, ConsolidadorDeVendas.class);
        query.setParameter(1, listaDeFiliais);
        query.setParameter(2, dataVendaIniSelecionada);
        query.setParameter(3, dataVendaFimSelecionada);
       
       
        try {
           return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
    
}
