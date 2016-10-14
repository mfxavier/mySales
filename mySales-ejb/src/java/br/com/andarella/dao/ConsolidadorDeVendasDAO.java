package br.com.andarella.dao;

import br.com.domain.ConsolidadorDeVendas;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ConsolidadorDeVendasDAO {
    public List<ConsolidadorDeVendas> getListarConsolidadorDeVendas(String listaDeFiliais, 
                                                                    String dataVendaIniSelecionada, 
                                                                    String dataVendaFimSelecionada);
    
    public List<ConsolidadorDeVendas> getListarConsolidadorDeVendasStart(String dataVendaIniSelecionada, 
                                                                         String dataVendaFimSelecionada);
    
    
    public List<ConsolidadorDeVendas> getGraficoDePercentualdeVendasStart(String dataVendaIniSelecionada, 
                                                                          String dataVendaFimSelecionada);
    
    public List<ConsolidadorDeVendas> getGraficoDePercentualdeVendas(String listaDeFiliais,
                                                                     String dataVendaIniSelecionada, 
                                                                     String dataVendaFimSelecionada);
    
    public List<ConsolidadorDeVendas> getListarMovimentacoesDiariasStart(String dataVendaIniSelecionada, 
                                                                          String dataVendaFimSelecionada);
    
    public List<ConsolidadorDeVendas> getListarMovimentacoesDiarias(String listaDeFiliais,
                                                                    String dataVendaIniSelecionada, 
                                                                    String dataVendaFimSelecionada);
    
    public List<ConsolidadorDeVendas> getListarMovimentacoesDiariasPorFilial(String listaDeFiliais,
                                                                    String dataVendaIniSelecionada, 
                                                                    String dataVendaFimSelecionada);
    
    public List<ConsolidadorDeVendas> getListarTotaisMovimentacoesDiariasPorFilial(String listaDeFiliais,
                                                                    String dataVendaIniSelecionada, 
                                                                    String dataVendaFimSelecionada);
    
    public List<ConsolidadorDeVendas> getGraficoPercentualPorLinhaProdutoStart(String dataVendaIniSelecionada, 
                                                                              String dataVendaFimSelecionada);
    
    public List<ConsolidadorDeVendas> getGraficoPercentualPorLinhaProduto(String listaDeFiliais,
                                                                    String dataVendaIniSelecionada, 
                                                                    String dataVendaFimSelecionada);
    
    
    public List<ConsolidadorDeVendas> getListarMovimentacoesPorGrupoProdutoStart(String dataVendaIniSelecionada, 
                                                                              String dataVendaFimSelecionada);
    
    public List<ConsolidadorDeVendas> getListarMovimentacoesPorGrupoProduto(String listaDeFiliais,
                                                                    String dataVendaIniSelecionada, 
                                                                    String dataVendaFimSelecionada);
    
     public List<ConsolidadorDeVendas> getTotaisPorGrupoProdutoStart(String dataVendaIniSelecionada, 
                                                                     String dataVendaFimSelecionada);
     
      public List<ConsolidadorDeVendas> getTotaisPorGrupoProduto(String listaDeFiliais,
                                                                 String dataVendaIniSelecionada, 
                                                                 String dataVendaFimSelecionada,
                                                                 String vendedorApelido);
      
      public List<ConsolidadorDeVendas> getTotaisPorVendedoresCategorias(String listaDeFiliais,
                                                                 String dataVendaIniSelecionada, 
                                                                 String dataVendaFimSelecionada,
                                                                 String vendedor);
     
      public List<ConsolidadorDeVendas> getListarVendedoresStart(String dataVendaIniSelecionada, 
                                                                 String dataVendaFimSelecionada);
     
      public List<ConsolidadorDeVendas> getListarVendedores(String listaDeFiliais,
                                                            String dataVendaIniSelecionada, 
                                                            String dataVendaFimSelecionada);
      
      
      public List<ConsolidadorDeVendas> getVendasPorCategoriasStart(String dataVendaIniSelecionada, 
                                                                    String dataVendaFimSelecionada);
      
      
      public List<ConsolidadorDeVendas> getVendasPorCategorias(String listaDeFiliais,
                                                               String dataVendaIniSelecionada, 
                                                               String dataVendaFimSelecionada);
      
      
     public List<ConsolidadorDeVendas> getGraficoGradeMediaStart(
                                                            String dataVendaIniSelecionada, 
                                                            String dataVendaFimSelecionada);
     
     public List<ConsolidadorDeVendas> getGraficoGradeMedia(String listaDeFiliais,
                                                            String dataVendaIniSelecionada, 
                                                            String dataVendaFimSelecionada);
    
    
}
