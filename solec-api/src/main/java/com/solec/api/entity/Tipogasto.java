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
 * @author elfo_
 */
@Entity
@Table(name = "tipogasto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipogasto.findAll", query = "SELECT t FROM Tipogasto t"),
    @NamedQuery(name = "Tipogasto.findByIdtipogasto", query = "SELECT t FROM Tipogasto t WHERE t.idtipogasto = :idtipogasto"),
    @NamedQuery(name = "Tipogasto.findByNombregasto", query = "SELECT t FROM Tipogasto t WHERE t.nombregasto = :nombregasto"),
    @NamedQuery(name = "Tipogasto.findByDescripciongasto", query = "SELECT t FROM Tipogasto t WHERE t.descripciongasto = :descripciongasto"),
    @NamedQuery(name = "Tipogasto.findByFechacreacion", query = "SELECT t FROM Tipogasto t WHERE t.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Tipogasto.findByUsuariocreacion", query = "SELECT t FROM Tipogasto t WHERE t.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Tipogasto.findByFechaeliminacion", query = "SELECT t FROM Tipogasto t WHERE t.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Tipogasto.findByUsuarioeliminacion", query = "SELECT t FROM Tipogasto t WHERE t.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Tipogasto.findByActivo", query = "SELECT t FROM Tipogasto t WHERE t.activo = :activo")})
public class Tipogasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipogasto")
    private Integer idtipogasto;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "nombregasto")
    private String nombregasto;
    
    @Size(max = 2000)
    @Column(name = "descripciongasto")
    private String descripciongasto;
    
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
    
    @Column(name = "fechaeliminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaeliminacion;
    
    @Size(max = 50)
    @Column(name = "usuarioeliminacion")
    private String usuarioeliminacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipogasto", fetch = FetchType.LAZY)
    private List<Detallepresupuesto> detallepresupuestoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipogasto", fetch = FetchType.LAZY)
    private List<Detallegasto> detallegastoList;

    public Tipogasto() {
    }

    public Tipogasto(Integer idtipogasto) {
        this.idtipogasto = idtipogasto;
    }

    public Tipogasto(Integer idtipogasto, String nombregasto, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idtipogasto = idtipogasto;
        this.nombregasto = nombregasto;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdtipogasto() {
        return idtipogasto;
    }

    public void setIdtipogasto(Integer idtipogasto) {
        this.idtipogasto = idtipogasto;
    }

    public String getNombregasto() {
        return nombregasto;
    }

    public void setNombregasto(String nombregasto) {
        this.nombregasto = nombregasto;
    }

    public String getDescripciongasto() {
        return descripciongasto;
    }

    public void setDescripciongasto(String descripciongasto) {
        this.descripciongasto = descripciongasto;
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

    public Date getFechaeliminacion() {
        return fechaeliminacion;
    }

    public void setFechaeliminacion(Date fechaeliminacion) {
        this.fechaeliminacion = fechaeliminacion;
    }

    public String getUsuarioeliminacion() {
        return usuarioeliminacion;
    }

    public void setUsuarioeliminacion(String usuarioeliminacion) {
        this.usuarioeliminacion = usuarioeliminacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Detallepresupuesto> getDetallepresupuestoList() {
        return detallepresupuestoList;
    }

    public void setDetallepresupuestoList(List<Detallepresupuesto> detallepresupuestoList) {
        this.detallepresupuestoList = detallepresupuestoList;
    }

    @XmlTransient
    public List<Detallegasto> getDetallegastoList() {
        return detallegastoList;
    }

    public void setDetallegastoList(List<Detallegasto> detallegastoList) {
        this.detallegastoList = detallegastoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipogasto != null ? idtipogasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipogasto)) {
            return false;
        }
        Tipogasto other = (Tipogasto) object;
        if ((this.idtipogasto == null && other.idtipogasto != null) || (this.idtipogasto != null && !this.idtipogasto.equals(other.idtipogasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Tipogasto[ idtipogasto=" + idtipogasto + " ]";
    }
    
}
