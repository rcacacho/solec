package com.solec.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rcacacho
 */
@Entity
@Table(name = "desembolso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desembolso.findAll", query = "SELECT d FROM Desembolso d"),
    @NamedQuery(name = "Desembolso.findByIddesembolso", query = "SELECT d FROM Desembolso d WHERE d.iddesembolso = :iddesembolso"),
    @NamedQuery(name = "Desembolso.findByCantidad", query = "SELECT d FROM Desembolso d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Desembolso.findByFechacreacion", query = "SELECT d FROM Desembolso d WHERE d.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Desembolso.findByUsuariocreacion", query = "SELECT d FROM Desembolso d WHERE d.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Desembolso.findByActivo", query = "SELECT d FROM Desembolso d WHERE d.activo = :activo")})
public class Desembolso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddesembolso")
    private Integer iddesembolso;

    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private float cantidad;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddesembolso", fetch = FetchType.LAZY)
    private List<Proyectodesembolso> proyectodesembolsoList;

    public Desembolso() {
    }

    public Desembolso(Integer iddesembolso) {
        this.iddesembolso = iddesembolso;
    }

    public Desembolso(Integer iddesembolso, float cantidad, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.iddesembolso = iddesembolso;
        this.cantidad = cantidad;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIddesembolso() {
        return iddesembolso;
    }

    public void setIddesembolso(Integer iddesembolso) {
        this.iddesembolso = iddesembolso;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
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
        hash += (iddesembolso != null ? iddesembolso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desembolso)) {
            return false;
        }
        Desembolso other = (Desembolso) object;
        if ((this.iddesembolso == null && other.iddesembolso != null) || (this.iddesembolso != null && !this.iddesembolso.equals(other.iddesembolso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Desembolso[ iddesembolso=" + iddesembolso + " ]";
    }

    @XmlTransient
    public List<Proyectodesembolso> getProyectodesembolsoList() {
        return proyectodesembolsoList;
    }

    public void setProyectodesembolsoList(List<Proyectodesembolso> proyectodesembolsoList) {
        this.proyectodesembolsoList = proyectodesembolsoList;
    }

}
