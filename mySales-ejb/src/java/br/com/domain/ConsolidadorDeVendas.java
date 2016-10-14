package br.com.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marlon.xavier
 */
@Entity
@Table(name = "vwConsolidadorDeVendas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsolidadorDeVendas.findAll", query = "SELECT c FROM ConsolidadorDeVendas c"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByFilial", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.filial = :filial"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByVendedorApelido", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.vendedorApelido = :vendedorApelido"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByValorLiquidoVenda", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.valorLiquidoVenda = :valorLiquidoVenda"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByValorMeta", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.valorMeta = :valorMeta"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByQtdeLiquida", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.qtdeLiquida = :qtdeLiquida"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByQtdeTroca", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.qtdeTroca = :qtdeTroca"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByValorTroca", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.valorTroca = :valorTroca"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByPecasAtendimento", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.pecasAtendimento = :pecasAtendimento"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByVendaMedia", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.vendaMedia = :vendaMedia"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByBolsasAtendimento", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.bolsasAtendimento = :bolsasAtendimento"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByCalcadosAtendimento", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.calcadosAtendimento = :calcadosAtendimento"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByOutrosAtendimento", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.outrosAtendimento = :outrosAtendimento"),
    @NamedQuery(name = "ConsolidadorDeVendas.findByData", query = "SELECT c FROM ConsolidadorDeVendas c WHERE c.data = :data")})
public class ConsolidadorDeVendas implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    
    @Column(name = "ID_CONSOLIDADOR_DE_VENDAS")
    @Id
    private Integer idConsolidadorDeVendas;
    
    
    @Basic(optional = false)
    @Size(min = 1, max = 1000)
    @Column(name = "FILIAL")
    private String filial;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "VENDEDOR_APELIDO")
    private String vendedorApelido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_LIQUIDO_VENDA")
    private BigDecimal valorLiquidoVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_META")
    private BigDecimal valorMeta;
    @Column(name = "QTDE_LIQUIDA")
    private Integer qtdeLiquida;
    @Column(name = "QTDE_TROCA")
    private Integer qtdeTroca;
    @Column(name = "VALOR_TROCA")
    private BigDecimal valorTroca;
    
    @Column(name = "PECAS_ATENDIMENTO")
    private BigDecimal pecasAtendimento;
    
    @Column(name = "VENDA_MEDIA")
    private BigDecimal vendaMedia;
    
    @Column(name = "PRECO_MEDIO")
    private BigDecimal precoMedio;
    
    @Column(name = "BOLSAS_ATENDIMENTO")
    private BigDecimal bolsasAtendimento;
    
    @Column(name = "CALCADOS_ATENDIMENTO")
    private BigDecimal calcadosAtendimento;
    
    @Column(name = "OUTROS_ATENDIMENTO")
    private BigDecimal outrosAtendimento;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date    dataVendaIni;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date    dataVendaFim;
    
    
    private float   valor_venda_filial;
    private float   valor_total_filiais;
    private float   percentual_vendas;
    
    private int     qtde_vendida;
    private float   venda_bruta;
    
    private String linha;
    private int    total_linha;
    private float  percentual;
    
    private String grupo_produto;
    private int qtde_venda;
    private float valor_venda;
    
    private String vendedor;
    
    private String mesVenda;
    
    private List<String> listaDeFiliais;
   
    @Column(name = "FILIAL_APELIDO")
    private String filialApelido;
    
    @Column(name = "SIGLA_FILIAL")
    private String siglaFilial;
    
    private String tamanho;
    
    
    private int estoque;
    private BigDecimal giro;
    
    
    
    public ConsolidadorDeVendas() {
    }

    public Integer getIdConsolidadorDeVendas() {
        return idConsolidadorDeVendas;
    }

    public void setIdConsolidadorDeVendas(Integer idConsolidadorDeVendas) {
        this.idConsolidadorDeVendas = idConsolidadorDeVendas;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getVendedorApelido() {
        return vendedorApelido;
    }

    public void setVendedorApelido(String vendedorApelido) {
        this.vendedorApelido = vendedorApelido;
    }

    public BigDecimal getValorLiquidoVenda() {
        return valorLiquidoVenda;
    }

    public void setValorLiquidoVenda(BigDecimal valorLiquidoVenda) {
        this.valorLiquidoVenda = valorLiquidoVenda;
    }

    public BigDecimal getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(BigDecimal valorMeta) {
        this.valorMeta = valorMeta;
    }

    public Integer getQtdeLiquida() {
        return qtdeLiquida;
    }

    public void setQtdeLiquida(Integer qtdeLiquida) {
        this.qtdeLiquida = qtdeLiquida;
    }

    public Integer getQtdeTroca() {
        return qtdeTroca;
    }

    public void setQtdeTroca(Integer qtdeTroca) {
        this.qtdeTroca = qtdeTroca;
    }

    public BigDecimal getValorTroca() {
        return valorTroca;
    }

    public void setValorTroca(BigDecimal valorTroca) {
        this.valorTroca = valorTroca;
    }

    public BigDecimal getPecasAtendimento() {
        return pecasAtendimento;
    }

    public void setPecasAtendimento(BigDecimal pecasAtendimento) {
        this.pecasAtendimento = pecasAtendimento;
    }

    public BigDecimal getVendaMedia() {
        return vendaMedia;
    }

    public void setVendaMedia(BigDecimal vendaMedia) {
        this.vendaMedia = vendaMedia;
    }

    public BigDecimal getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(BigDecimal precoMedio) {
        this.precoMedio = precoMedio;
    }

    
    
    
    public BigDecimal getBolsasAtendimento() {
        return bolsasAtendimento;
    }

    public void setBolsasAtendimento(BigDecimal bolsasAtendimento) {
        this.bolsasAtendimento = bolsasAtendimento;
    }

    public BigDecimal getCalcadosAtendimento() {
        return calcadosAtendimento;
    }

    public void setCalcadosAtendimento(BigDecimal calcadosAtendimento) {
        this.calcadosAtendimento = calcadosAtendimento;
    }

    public BigDecimal getOutrosAtendimento() {
        return outrosAtendimento;
    }

    public void setOutrosAtendimento(BigDecimal outrosAtendimento) {
        this.outrosAtendimento = outrosAtendimento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataVendaIni() {
        return dataVendaIni;
    }

    public void setDataVendaIni(Date dataVendaIni) {
        this.dataVendaIni = dataVendaIni;
    }

    public Date getDataVendaFim() {
        return dataVendaFim;
    }

    public void setDataVendaFim(Date dataVendaFim) {
        this.dataVendaFim = dataVendaFim;
    }

    public float getValor_venda_filial() {
        return valor_venda_filial;
    }

    public void setValor_venda_filial(float valor_venda_filial) {
        this.valor_venda_filial = valor_venda_filial;
    }

    public float getValor_total_filiais() {
        return valor_total_filiais;
    }

    public void setValor_total_filiais(float valor_total_filiais) {
        this.valor_total_filiais = valor_total_filiais;
    }

    public float getPercentual_vendas() {
        return percentual_vendas;
    }

    public void setPercentual_vendas(float percentual_vendas) {
        this.percentual_vendas = percentual_vendas;
    }

    public int getQtde_vendida() {
        return qtde_vendida;
    }

    public void setQtde_vendida(int qtde_vendida) {
        this.qtde_vendida = qtde_vendida;
    }

    public float getVenda_bruta() {
        return venda_bruta;
    }

    public void setVenda_bruta(float venda_bruta) {
        this.venda_bruta = venda_bruta;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public int getTotal_linha() {
        return total_linha;
    }

    public void setTotal_linha(int total_linha) {
        this.total_linha = total_linha;
    }

    public float getPercentual() {
        return percentual;
    }

    public void setPercentual(float percentual) {
        this.percentual = percentual;
    }

    public String getGrupo_produto() {
        return grupo_produto;
    }

    public void setGrupo_produto(String grupo_produto) {
        this.grupo_produto = grupo_produto;
    }

    public int getQtde_venda() {
        return qtde_venda;
    }

    public void setQtde_venda(int qtde_venda) {
        this.qtde_venda = qtde_venda;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getMesVenda() {
        return mesVenda;
    }

    public void setMesVenda(String mesVenda) {
        this.mesVenda = mesVenda;
    }

    public List<String> getListaDeFiliais() {
        return listaDeFiliais;
    }

    public void setListaDeFiliais(List<String> listaDeFiliais) {
        this.listaDeFiliais = listaDeFiliais;
    }

    public String getFilialApelido() {
        return filialApelido;
    }

    public void setFilialApelido(String filialApelido) {
        this.filialApelido = filialApelido;
    }

    public String getSiglaFilial() {
        return siglaFilial;
    }

    public void setSiglaFilial(String siglaFilial) {
        this.siglaFilial = siglaFilial;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public BigDecimal getGiro() {
        return giro;
    }

    public void setGiro(BigDecimal giro) {
        this.giro = giro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idConsolidadorDeVendas);
        hash = 17 * hash + Objects.hashCode(this.filial);
        hash = 17 * hash + Objects.hashCode(this.vendedorApelido);
        hash = 17 * hash + Objects.hashCode(this.valorLiquidoVenda);
        hash = 17 * hash + Objects.hashCode(this.valorMeta);
        hash = 17 * hash + Objects.hashCode(this.qtdeLiquida);
        hash = 17 * hash + Objects.hashCode(this.qtdeTroca);
        hash = 17 * hash + Objects.hashCode(this.valorTroca);
        hash = 17 * hash + Objects.hashCode(this.pecasAtendimento);
        hash = 17 * hash + Objects.hashCode(this.vendaMedia);
        hash = 17 * hash + Objects.hashCode(this.precoMedio);
        hash = 17 * hash + Objects.hashCode(this.bolsasAtendimento);
        hash = 17 * hash + Objects.hashCode(this.calcadosAtendimento);
        hash = 17 * hash + Objects.hashCode(this.outrosAtendimento);
        hash = 17 * hash + Objects.hashCode(this.data);
        hash = 17 * hash + Objects.hashCode(this.dataVendaIni);
        hash = 17 * hash + Objects.hashCode(this.dataVendaFim);
        hash = 17 * hash + Float.floatToIntBits(this.valor_venda_filial);
        hash = 17 * hash + Float.floatToIntBits(this.valor_total_filiais);
        hash = 17 * hash + Float.floatToIntBits(this.percentual_vendas);
        hash = 17 * hash + this.qtde_vendida;
        hash = 17 * hash + Float.floatToIntBits(this.venda_bruta);
        hash = 17 * hash + Objects.hashCode(this.linha);
        hash = 17 * hash + this.total_linha;
        hash = 17 * hash + Float.floatToIntBits(this.percentual);
        hash = 17 * hash + Objects.hashCode(this.grupo_produto);
        hash = 17 * hash + this.qtde_venda;
        hash = 17 * hash + Float.floatToIntBits(this.valor_venda);
        hash = 17 * hash + Objects.hashCode(this.vendedor);
        hash = 17 * hash + Objects.hashCode(this.mesVenda);
        hash = 17 * hash + Objects.hashCode(this.listaDeFiliais);
        hash = 17 * hash + Objects.hashCode(this.filialApelido);
        hash = 17 * hash + Objects.hashCode(this.siglaFilial);
        hash = 17 * hash + Objects.hashCode(this.tamanho);
        hash = 17 * hash + this.estoque;
        hash = 17 * hash + Objects.hashCode(this.giro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConsolidadorDeVendas other = (ConsolidadorDeVendas) obj;
        if (!Objects.equals(this.idConsolidadorDeVendas, other.idConsolidadorDeVendas)) {
            return false;
        }
        if (!Objects.equals(this.filial, other.filial)) {
            return false;
        }
        if (!Objects.equals(this.vendedorApelido, other.vendedorApelido)) {
            return false;
        }
        if (!Objects.equals(this.valorLiquidoVenda, other.valorLiquidoVenda)) {
            return false;
        }
        if (!Objects.equals(this.valorMeta, other.valorMeta)) {
            return false;
        }
        if (!Objects.equals(this.qtdeLiquida, other.qtdeLiquida)) {
            return false;
        }
        if (!Objects.equals(this.qtdeTroca, other.qtdeTroca)) {
            return false;
        }
        if (!Objects.equals(this.valorTroca, other.valorTroca)) {
            return false;
        }
        if (!Objects.equals(this.pecasAtendimento, other.pecasAtendimento)) {
            return false;
        }
        if (!Objects.equals(this.vendaMedia, other.vendaMedia)) {
            return false;
        }
        if (!Objects.equals(this.precoMedio, other.precoMedio)) {
            return false;
        }
        if (!Objects.equals(this.bolsasAtendimento, other.bolsasAtendimento)) {
            return false;
        }
        if (!Objects.equals(this.calcadosAtendimento, other.calcadosAtendimento)) {
            return false;
        }
        if (!Objects.equals(this.outrosAtendimento, other.outrosAtendimento)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.dataVendaIni, other.dataVendaIni)) {
            return false;
        }
        if (!Objects.equals(this.dataVendaFim, other.dataVendaFim)) {
            return false;
        }
        if (Float.floatToIntBits(this.valor_venda_filial) != Float.floatToIntBits(other.valor_venda_filial)) {
            return false;
        }
        if (Float.floatToIntBits(this.valor_total_filiais) != Float.floatToIntBits(other.valor_total_filiais)) {
            return false;
        }
        if (Float.floatToIntBits(this.percentual_vendas) != Float.floatToIntBits(other.percentual_vendas)) {
            return false;
        }
        if (this.qtde_vendida != other.qtde_vendida) {
            return false;
        }
        if (Float.floatToIntBits(this.venda_bruta) != Float.floatToIntBits(other.venda_bruta)) {
            return false;
        }
        if (!Objects.equals(this.linha, other.linha)) {
            return false;
        }
        if (this.total_linha != other.total_linha) {
            return false;
        }
        if (Float.floatToIntBits(this.percentual) != Float.floatToIntBits(other.percentual)) {
            return false;
        }
        if (!Objects.equals(this.grupo_produto, other.grupo_produto)) {
            return false;
        }
        if (this.qtde_venda != other.qtde_venda) {
            return false;
        }
        if (Float.floatToIntBits(this.valor_venda) != Float.floatToIntBits(other.valor_venda)) {
            return false;
        }
        if (!Objects.equals(this.vendedor, other.vendedor)) {
            return false;
        }
        if (!Objects.equals(this.mesVenda, other.mesVenda)) {
            return false;
        }
        if (!Objects.equals(this.listaDeFiliais, other.listaDeFiliais)) {
            return false;
        }
        if (!Objects.equals(this.filialApelido, other.filialApelido)) {
            return false;
        }
        if (!Objects.equals(this.siglaFilial, other.siglaFilial)) {
            return false;
        }
        if (!Objects.equals(this.tamanho, other.tamanho)) {
            return false;
        }
        if (this.estoque != other.estoque) {
            return false;
        }
        if (!Objects.equals(this.giro, other.giro)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConsolidadorDeVendas{" + "idConsolidadorDeVendas=" + idConsolidadorDeVendas + ", filial=" + filial + ", vendedorApelido=" + vendedorApelido + ", valorLiquidoVenda=" + valorLiquidoVenda + ", valorMeta=" + valorMeta + ", qtdeLiquida=" + qtdeLiquida + ", qtdeTroca=" + qtdeTroca + ", valorTroca=" + valorTroca + ", pecasAtendimento=" + pecasAtendimento + ", vendaMedia=" + vendaMedia + ", precoMedio=" + precoMedio + ", bolsasAtendimento=" + bolsasAtendimento + ", calcadosAtendimento=" + calcadosAtendimento + ", outrosAtendimento=" + outrosAtendimento + ", data=" + data + ", dataVendaIni=" + dataVendaIni + ", dataVendaFim=" + dataVendaFim + ", valor_venda_filial=" + valor_venda_filial + ", valor_total_filiais=" + valor_total_filiais + ", percentual_vendas=" + percentual_vendas + ", qtde_vendida=" + qtde_vendida + ", venda_bruta=" + venda_bruta + ", linha=" + linha + ", total_linha=" + total_linha + ", percentual=" + percentual + ", grupo_produto=" + grupo_produto + ", qtde_venda=" + qtde_venda + ", valor_venda=" + valor_venda + ", vendedor=" + vendedor + ", mesVenda=" + mesVenda + ", listaDeFiliais=" + listaDeFiliais + ", filialApelido=" + filialApelido + ", siglaFilial=" + siglaFilial + ", tamanho=" + tamanho + ", estoque=" + estoque + ", giro=" + giro + '}';
    }
    
    

    
    
    
        
    
    
}
