package br.com.andarella.filiais;

import br.com.andarella.dao.FiliaisDAO;
import br.com.domain.Filiais;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="filiaisMB")
@RequestScoped
public class filiaisMB {

    @EJB
    private FiliaisDAO filiaisDAO;
    
    private Filiais filiais = new Filiais();
    
    private String listaFiliais;
    private Date dtVendaIni;
    private Date dtVendaFim;
    
    public List<Filiais> obterListaDeFiliais(){
        if(filiaisDAO.getListarFiliais().isEmpty()){
            return null;
        }
        
        return filiaisDAO.getListarFiliais();
    }
    
    public String mensagem(){
       System.out.println("TESTE-->>>");
        return null;
    }
    
    public filiaisMB() {
    }

    public Filiais getFiliais() {
        return filiais;
    }

    public void setFiliais(Filiais filiais) {
        this.filiais = filiais;
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

    public String getListaFiliais() {
        return listaFiliais;
    }

    public void setListaFiliais(String listaFiliais) {
        this.listaFiliais = listaFiliais;
    }
    
    
    //Metodos para adicionar mensagens
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
    private void sendInfoMessageToUser(String message){
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
		
    private void sendErrorMessageToUser(String message){
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

 

  
    
    
    
    
    
}
