package com.solec.api.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rcacacho
 */
@Entity
@Table(name = "configuracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuracion.findAll", query = "SELECT c FROM Configuracion c"),
    @NamedQuery(name = "Configuracion.findByIdconfiguracion", query = "SELECT c FROM Configuracion c WHERE c.idconfiguracion = :idconfiguracion"),
    @NamedQuery(name = "Configuracion.findByParametro", query = "SELECT c FROM Configuracion c WHERE c.parametro = :parametro"),
    @NamedQuery(name = "Configuracion.findByValor", query = "SELECT c FROM Configuracion c WHERE c.valor = :valor"),
    @NamedQuery(name = "Configuracion.findByDescripcion", query = "SELECT c FROM Configuracion c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Configuracion.findByActivo", query = "SELECT c FROM Configuracion c WHERE c.activo = :activo")})
public class Configuracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconfiguracion")
    private Integer idconfiguracion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "parametro")
    private String parametro;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "valor")
    private String valor;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;

    public Configuracion() {
    }

    public Configuracion(Integer idconfiguracion) {
        this.idconfiguracion = idconfiguracion;
    }

    public Configuracion(Integer idconfiguracion, String parametro, String valor, String descripcion, boolean activo) {
        this.idconfiguracion = idconfiguracion;
        this.parametro = parametro;
        this.valor = valor;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Integer getIdconfiguracion() {
        return idconfiguracion;
    }

    public void setIdconfiguracion(Integer idconfiguracion) {
        this.idconfiguracion = idconfiguracion;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconfiguracion != null ? idconfiguracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuracion)) {
            return false;
        }
        Configuracion other = (Configuracion) object;
        if ((this.idconfiguracion == null && other.idconfiguracion != null) || (this.idconfiguracion != null && !this.idconfiguracion.equals(other.idconfiguracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Configuracion[ idconfiguracion=" + idconfiguracion + " ]";
    }
    
}
