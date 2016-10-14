/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andarella.parametros;

import br.com.domain.ConsolidadorDeVendas;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="parametrosManagedBean")
@RequestScoped
public class ParametrosManagedBean {
  
    
    public ConsolidadorDeVendas consolidador;
    
    
    private String listaFiliais;
    private Date dtVendaIni;
    private Date dtVendaFim;
    
    public ParametrosManagedBean() {
    }
    
    
    public ConsolidadorDeVendas getConsolidador() {
        return consolidador;
    }

    public void setConsolidador(ConsolidadorDeVendas consolidador) {
        this.consolidador = consolidador;
    }
    
    public void testarParametros(){
//        System.out.println("<-- OBJETOS -->");
//        System.out.println("FILIAIS -->"+consolidador.getFilial());
//        System.out.println("DT_INI  -->"+consolidador.getData_venda_ini());
//        System.out.println("DT_FIM  -->"+consolidador.getData_venda_fim());
        
        
        System.out.println("<-- PARAMETROS -->");
        System.out.println("FILIAIS -->"+listaFiliais);
        System.out.println("DT_INI  -->"+dtVendaIni);
        System.out.println("DT_FIM  -->"+dtVendaFim);
    }

    public String getListaFiliais() {
        return listaFiliais;
    }

    public void setListaFiliais(String listaFiliais) {
        this.listaFiliais = listaFiliais;
    }

    public Date getDtVendaIni() {
        return dtVendaIni;
    }

    public void setDtVendaIni(Date dtVendaIni) {
        this.dtVendaIni = dtVendaIni;
    }

    public Date getDtVendaFim() {
        return dtVendaFim;
    }

    public void setDtVendaFim(Date dtVendaFim) {
        this.dtVendaFim = dtVendaFim;
    }
    
    
}
