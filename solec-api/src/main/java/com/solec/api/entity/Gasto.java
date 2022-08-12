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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "gasto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gasto.findAll", query = "SELECT g FROM Gasto g"),
    @NamedQuery(name = "Gasto.findByIdgasto", query = "SELECT g FROM Gasto g WHERE g.idgasto = :idgasto"),
    @NamedQuery(name = "Gasto.findByProyecto", query = "SELECT g FROM Gasto g WHERE g.proyecto = :proyecto"),
    @NamedQuery(name = "Gasto.findByDireccion", query = "SELECT g FROM Gasto g WHERE g.direccion = :direccion"),
    @NamedQuery(name = "Gasto.findByFechacrecion", query = "SELECT g FROM Gasto g WHERE g.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Gasto.findByUsuariocreacion", query = "SELECT g FROM Gasto g WHERE g.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Gasto.findByFechamodificacion", query = "SELECT g FROM Gasto g WHERE g.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Gasto.findByUsuariomodificacion", query = "SELECT g FROM Gasto g WHERE g.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Gasto.findByActivo", query = "SELECT g FROM Gasto g WHERE g.activo = :activo")})
public class Gasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgasto")
    private Integer idgasto;
    
    @Size(max = 500)
    @Column(name = "proyecto")
    private String proyecto;
    
    @Size(max = 500)
    @Column(name = "direccion")
    private String direccion;
    
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
    
    @Column(name = "fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    
    @Size(max = 50)
    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgasto", fetch = FetchType.LAZY)
    private List<Detallegasto> detallegastoList;
    
    @JoinColumn(name = "idpresupuesto", referencedColumnName = "idpresupuesto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Presupuesto idpresupuesto;

    public Gasto() {
    }

    public Gasto(Integer idgasto) {
        this.idgasto = idgasto;
    }

    public Gasto(Integer idgasto, Date fechacrecion, String usuariocreacion, boolean activo) {
        this.idgasto = idgasto;
        this.fechacreacion = fechacrecion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(Integer idgasto) {
        this.idgasto = idgasto;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public String getUsuariomodificacion() {
        return usuariomodificacion;
    }

    public void setUsuariomodificacion(String usuariomodificacion) {
        this.usuariomodificacion = usuariomodificacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Detallegasto> getDetallegastoList() {
        return detallegastoList;
    }

    public void setDetallegastoList(List<Detallegasto> detallegastoList) {
        this.detallegastoList = detallegastoList;
    }

    public Presupuesto getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Presupuesto idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgasto != null ? idgasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gasto)) {
            return false;
        }
        Gasto other = (Gasto) object;
        if ((this.idgasto == null && other.idgasto != null) || (this.idgasto != null && !this.idgasto.equals(other.idgasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Gasto[ idgasto=" + idgasto + " ]";
    }
    
}
