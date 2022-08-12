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
@Table(name = "detallegasto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallegasto.findAll", query = "SELECT d FROM Detallegasto d"),
    @NamedQuery(name = "Detallegasto.findByIddetallegasto", query = "SELECT d FROM Detallegasto d WHERE d.iddetallegasto = :iddetallegasto"),
    @NamedQuery(name = "Detallegasto.findByCantidad", query = "SELECT d FROM Detallegasto d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallegasto.findByValorunitario", query = "SELECT d FROM Detallegasto d WHERE d.valorunitario = :valorunitario"),
    @NamedQuery(name = "Detallegasto.findByTotal", query = "SELECT d FROM Detallegasto d WHERE d.total = :total"),
    @NamedQuery(name = "Detallegasto.findByFechacrecion", query = "SELECT d FROM Detallegasto d WHERE d.fechacrecion = :fechacrecion"),
    @NamedQuery(name = "Detallegasto.findByUsuariocreacion", query = "SELECT d FROM Detallegasto d WHERE d.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Detallegasto.findByActivo", query = "SELECT d FROM Detallegasto d WHERE d.activo = :activo")})
public class Detallegasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallegasto")
    private Integer iddetallegasto;
    
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
    @Column(name = "fechacrecion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacrecion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuariocreacion")
    private String usuariocreacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    
    @JoinColumn(name = "idgasto", referencedColumnName = "idgasto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Gasto idgasto;
    
    @JoinColumn(name = "idtipocantidad", referencedColumnName = "idtipocantidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipocantidad idtipocantidad;
    
    @JoinColumn(name = "idtipogasto", referencedColumnName = "idtipogasto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipogasto idtipogasto;

    public Detallegasto() {
    }

    public Detallegasto(Integer iddetallegasto) {
        this.iddetallegasto = iddetallegasto;
    }

    public Detallegasto(Integer iddetallegasto, Integer cantidad, float valorunitario, float total, Date fechacrecion, String usuariocreacion, boolean activo) {
        this.iddetallegasto = iddetallegasto;
        this.cantidad = cantidad;
        this.valorunitario = valorunitario;
        this.total = total;
        this.fechacrecion = fechacrecion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIddetallegasto() {
        return iddetallegasto;
    }

    public void setIddetallegasto(Integer iddetallegasto) {
        this.iddetallegasto = iddetallegasto;
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

    public Date getFechacrecion() {
        return fechacrecion;
    }

    public void setFechacrecion(Date fechacrecion) {
        this.fechacrecion = fechacrecion;
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

    public Gasto getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(Gasto idgasto) {
        this.idgasto = idgasto;
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
        hash += (iddetallegasto != null ? iddetallegasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallegasto)) {
            return false;
        }
        Detallegasto other = (Detallegasto) object;
        if ((this.iddetallegasto == null && other.iddetallegasto != null) || (this.iddetallegasto != null && !this.iddetallegasto.equals(other.iddetallegasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Detallegasto[ iddetallegasto=" + iddetallegasto + " ]";
    }
    
}
