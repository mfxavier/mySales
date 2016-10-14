package br.com.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-03T17:06:39")
@StaticMetamodel(ConsolidadorDeVendas.class)
public class ConsolidadorDeVendas_ { 

    public static volatile SingularAttribute<ConsolidadorDeVendas, Integer> qtde_venda;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Date> data;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Float> valor_total_filiais;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Integer> qtde_vendida;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Date> dataVendaFim;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> valorLiquidoVenda;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> pecasAtendimento;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Integer> qtdeTroca;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Float> percentual_vendas;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> calcadosAtendimento;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Integer> estoque;
    public static volatile SingularAttribute<ConsolidadorDeVendas, String> tamanho;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> valorTroca;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> vendaMedia;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Float> valor_venda_filial;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Float> venda_bruta;
    public static volatile SingularAttribute<ConsolidadorDeVendas, String> siglaFilial;
    public static volatile SingularAttribute<ConsolidadorDeVendas, String> filialApelido;
    public static volatile SingularAttribute<ConsolidadorDeVendas, List> listaDeFiliais;
    public static volatile SingularAttribute<ConsolidadorDeVendas, String> mesVenda;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Integer> qtdeLiquida;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Integer> total_linha;
    public static volatile SingularAttribute<ConsolidadorDeVendas, String> vendedor;
    public static volatile SingularAttribute<ConsolidadorDeVendas, String> filial;
    public static volatile SingularAttribute<ConsolidadorDeVendas, String> grupo_produto;
    public static volatile SingularAttribute<ConsolidadorDeVendas, String> linha;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Integer> idConsolidadorDeVendas;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> outrosAtendimento;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Date> dataVendaIni;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> giro;
    public static volatile SingularAttribute<ConsolidadorDeVendas, String> vendedorApelido;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Float> valor_venda;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> precoMedio;
    public static volatile SingularAttribute<ConsolidadorDeVendas, Float> percentual;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> valorMeta;
    public static volatile SingularAttribute<ConsolidadorDeVendas, BigDecimal> bolsasAtendimento;

}