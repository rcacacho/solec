package com.solec.api.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author rcacacho
 */
@Entity
@Table(name = "proyectodesembolso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectodesembolso.findAll", query = "SELECT p FROM Proyectodesembolso p"),
    @NamedQuery(name = "Proyectodesembolso.findByIdproyectodesembolso", query = "SELECT p FROM Proyectodesembolso p WHERE p.idproyectodesembolso = :idproyectodesembolso"),
    @NamedQuery(name = "Proyectodesembolso.findByUsuariocreacion", query = "SELECT p FROM Proyectodesembolso p WHERE p.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Proyectodesembolso.findByFechacreacion", query = "SELECT p FROM Proyectodesembolso p WHERE p.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Proyectodesembolso.findByActivo", query = "SELECT p FROM Proyectodesembolso p WHERE p.activo = :activo")})
public class Proyectodesembolso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproyectodesembolso")
    private Integer idproyectodesembolso;
    
   @Basic(optional = false)
    @NotNull
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuariocreacion")
    private String usuariocreacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    
    @JoinColumn(name = "iddesembolso", referencedColumnName = "iddesembolso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Desembolso iddesembolso;
    
    @JoinColumn(name = "idproyecto", referencedColumnName = "idpresupuesto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proyectos idproyecto;

    public Proyectodesembolso() {
    }

    public Proyectodesembolso(Integer idproyectodesembolso) {
        this.idproyectodesembolso = idproyectodesembolso;
    }

    public Proyectodesembolso(Integer idproyectodesembolso, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idproyectodesembolso = idproyectodesembolso;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdproyectodesembolso() {
        return idproyectodesembolso;
    }

    public void setIdproyectodesembolso(Integer idproyectodesembolso) {
        this.idproyectodesembolso = idproyectodesembolso;
    }

    public String getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Desembolso getIddesembolso() {
        return iddesembolso;
    }

    public void setIddesembolso(Desembolso iddesembolso) {
        this.iddesembolso = iddesembolso;
    }

    public Proyectos getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Proyectos idproyecto) {
        this.idproyecto = idproyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproyectodesembolso != null ? idproyectodesembolso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectodesembolso)) {
            return false;
        }
        Proyectodesembolso other = (Proyectodesembolso) object;
        if ((this.idproyectodesembolso == null && other.idproyectodesembolso != null) || (this.idproyectodesembolso != null && !this.idproyectodesembolso.equals(other.idproyectodesembolso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Proyectodesembolso[ idproyectodesembolso=" + idproyectodesembolso + " ]";
    }
    
}
