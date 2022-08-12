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
@Table(name = "presupuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Presupuesto.findAll", query = "SELECT p FROM Presupuesto p"),
    @NamedQuery(name = "Presupuesto.findByIdpresupuesto", query = "SELECT p FROM Presupuesto p WHERE p.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Presupuesto.findByNombre", query = "SELECT p FROM Presupuesto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Presupuesto.findByDireccion", query = "SELECT p FROM Presupuesto p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Presupuesto.findByFechacreacion", query = "SELECT p FROM Presupuesto p WHERE p.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Presupuesto.findByUsuariocreacion", query = "SELECT p FROM Presupuesto p WHERE p.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Presupuesto.findByActivo", query = "SELECT p FROM Presupuesto p WHERE p.activo = :activo")})
public class Presupuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpresupuesto")
    private Integer idpresupuesto;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre")
    private String nombre;
    
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
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpresupuesto", fetch = FetchType.LAZY)
    private List<Detallepresupuesto> detallepresupuestoList;
    
    @OneToMany(mappedBy = "idpresupuesto", fetch = FetchType.LAZY)
    private List<Gasto> gastoList;

    public Presupuesto() {
    }

    public Presupuesto(Integer idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public Presupuesto(Integer idpresupuesto, String nombre, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idpresupuesto = idpresupuesto;
        this.nombre = nombre;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Integer idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<Gasto> getGastoList() {
        return gastoList;
    }

    public void setGastoList(List<Gasto> gastoList) {
        this.gastoList = gastoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpresupuesto != null ? idpresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.idpresupuesto == null && other.idpresupuesto != null) || (this.idpresupuesto != null && !this.idpresupuesto.equals(other.idpresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Presupuesto[ idpresupuesto=" + idpresupuesto + " ]";
    }

}
