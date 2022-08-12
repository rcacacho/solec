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
 * @author elfo_
 */
@Entity
@Table(name = "detallepresupuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallepresupuesto.findAll", query = "SELECT d FROM Detallepresupuesto d"),
    @NamedQuery(name = "Detallepresupuesto.findByIddetallepresupuesto", query = "SELECT d FROM Detallepresupuesto d WHERE d.iddetallepresupuesto = :iddetallepresupuesto"),
    @NamedQuery(name = "Detallepresupuesto.findByCantidad", query = "SELECT d FROM Detallepresupuesto d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallepresupuesto.findByValorunitario", query = "SELECT d FROM Detallepresupuesto d WHERE d.valorunitario = :valorunitario"),
    @NamedQuery(name = "Detallepresupuesto.findByTotal", query = "SELECT d FROM Detallepresupuesto d WHERE d.total = :total"),
    @NamedQuery(name = "Detallepresupuesto.findByFechacreacion", query = "SELECT d FROM Detallepresupuesto d WHERE d.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Detallepresupuesto.findByUsuariocreacion", query = "SELECT d FROM Detallepresupuesto d WHERE d.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Detallepresupuesto.findByActivo", query = "SELECT d FROM Detallepresupuesto d WHERE d.activo = :activo")})
public class Detallepresupuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallepresupuesto")
    private Integer iddetallepresupuesto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valorunitario")
    private float valorunitario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private float total;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechacreacion")
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
    
    @JoinColumn(name = "idpresupuesto", referencedColumnName = "idpresupuesto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Presupuesto idpresupuesto;
    
    @JoinColumn(name = "idtipocantidad", referencedColumnName = "idtipocantidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipocantidad idtipocantidad;
    
    @JoinColumn(name = "idtipogasto", referencedColumnName = "idtipogasto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipogasto idtipogasto;

    public Detallepresupuesto() {
    }

    public Detallepresupuesto(Integer iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }

    public Detallepresupuesto(Integer iddetallepresupuesto, int cantidad, float valorunitario, float total, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.iddetallepresupuesto = iddetallepresupuesto;
        this.cantidad = cantidad;
        this.valorunitario = valorunitario;
        this.total = total;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIddetallepresupuesto() {
        return iddetallepresupuesto;
    }

    public void setIddetallepresupuesto(Integer iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public float getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(float valorunitario) {
        this.valorunitario = valorunitario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
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

    public Presupuesto getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Presupuesto idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public Tipocantidad getIdtipocantidad() {
        return idtipocantidad;
    }

    public void setIdtipocantidad(Tipocantidad idtipocantidad) {
        this.idtipocantidad = idtipocantidad;
    }

    public Tipogasto getIdtipogasto() {
        return idtipogasto;
    }

    public void setIdtipogasto(Tipogasto idtipogasto) {
        this.idtipogasto = idtipogasto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallepresupuesto != null ? iddetallepresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepresupuesto)) {
            return false;
        }
        Detallepresupuesto other = (Detallepresupuesto) object;
        if ((this.iddetallepresupuesto == null && other.iddetallepresupuesto != null) || (this.iddetallepresupuesto != null && !this.iddetallepresupuesto.equals(other.iddetallepresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Detallepresupuesto[ iddetallepresupuesto=" + iddetallepresupuesto + " ]";
    }

}
