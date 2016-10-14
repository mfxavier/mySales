/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marlon.xavier
 */
@Entity
@Table(name = "vwFiliais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filiais.findAll", query = "SELECT f FROM Filiais f where f.siglaFilial !='' order by f.siglaFilial"),
    @NamedQuery(name = "Filiais.findByFilial", query = "SELECT f FROM Filiais f WHERE f.filial = :filial"),
    @NamedQuery(name = "Filiais.findByAreaM2", query = "SELECT f FROM Filiais f WHERE f.areaM2 = :areaM2")})
public class Filiais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "FILIAL")
    @Id
    private String filial;
    @Column(name = "AREA_M2")
    private Integer areaM2;

    
    @Column(name = "SIGLA_FILIAL")
    @Size(min = 1, max = 2)
    private String siglaFilial;
    
    @Column(name = "FILIAL_APELIDO")
    @Size(min = 1, max = 2)
    private String filialApelido;
    
    public Filiais() {
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public Integer getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(Integer areaM2) {
        this.areaM2 = areaM2;
    }

    public String getSiglaFilial() {
        return siglaFilial;
    }

    public void setSiglaFilial(String siglaFilial) {
        this.siglaFilial = siglaFilial;
    }

    public String getFilialApelido() {
        return filialApelido;
    }

    public void setFilialApelido(String filialApelido) {
        this.filialApelido = filialApelido;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.filial);
        hash = 41 * hash + Objects.hashCode(this.areaM2);
        hash = 41 * hash + Objects.hashCode(this.siglaFilial);
        hash = 41 * hash + Objects.hashCode(this.filialApelido);
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
        final Filiais other = (Filiais) obj;
        if (!Objects.equals(this.filial, other.filial)) {
            return false;
        }
        if (!Objects.equals(this.areaM2, other.areaM2)) {
            return false;
        }
        if (!Objects.equals(this.siglaFilial, other.siglaFilial)) {
            return false;
        }
        if (!Objects.equals(this.filialApelido, other.filialApelido)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Filiais{" + "filial=" + filial + ", areaM2=" + areaM2 + ", siglaFilial=" + siglaFilial + ", filialApelido=" + filialApelido + '}';
    }

    
    
    
    
    
}
