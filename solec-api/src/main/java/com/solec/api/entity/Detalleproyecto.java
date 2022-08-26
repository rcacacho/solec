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
@Table(name = "detalleproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleproyecto.findAll", query = "SELECT d FROM Detalleproyecto d"),
    @NamedQuery(name = "Detalleproyecto.findByIddetallepresupuesto", query = "SELECT d FROM Detalleproyecto d WHERE d.iddetallepresupuesto = :iddetallepresupuesto"),
    @NamedQuery(name = "Detalleproyecto.findByDescripcion", query = "SELECT d FROM Detalleproyecto d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Detalleproyecto.findByCantidad", query = "SELECT d FROM Detalleproyecto d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalleproyecto.findByValorunitario", query = "SELECT d FROM Detalleproyecto d WHERE d.valorunitario = :valorunitario"),
    @NamedQuery(name = "Detalleproyecto.findByTotal", query = "SELECT d FROM Detalleproyecto d WHERE d.total = :total"),
    @NamedQuery(name = "Detalleproyecto.findByDirectorio", query = "SELECT d FROM Detalleproyecto d WHERE d.directorio = :directorio"),
    @NamedQuery(name = "Detalleproyecto.findByNombrearchivo", query = "SELECT d FROM Detalleproyecto d WHERE d.nombrearchivo = :nombrearchivo"),
    @NamedQuery(name = "Detalleproyecto.findByObservacion", query = "SELECT d FROM Detalleproyecto d WHERE d.observacion = :observacion"),
    @NamedQuery(name = "Detalleproyecto.findByFechacreacion", query = "SELECT d FROM Detalleproyecto d WHERE d.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Detalleproyecto.findByUsuariocreacion", query = "SELECT d FROM Detalleproyecto d WHERE d.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Detalleproyecto.findByActivo", query = "SELECT d FROM Detalleproyecto d WHERE d.activo = :activo")})
public class Detalleproyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallepresupuesto")
    private Integer iddetallepresupuesto;
    
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorunitario")
    private float valorunitario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private float total;
    
    @Size(max = 500)
    @Column(name = "directorio")
    private String directorio;
    
    @Size(max = 500)
    @Column(name = "nombrearchivo")
    private String nombrearchivo;
    
    @Size(max = 1000)
    @Column(name = "observacion")
    private String observacion;
    
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
    
    @JoinColumn(name = "idpresupuesto", referencedColumnName = "idpresupuesto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proyectos idpresupuesto;
    
    @JoinColumn(name = "idtipocantidad", referencedColumnName = "idtipocantidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tipocantidad idtipocantidad;
    
    @JoinColumn(name = "idtipogasto", referencedColumnName = "idtipogasto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tipogasto idtipogasto;

    public Detalleproyecto() {
    }

    public Detalleproyecto(Integer iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }

    public Detalleproyecto(Integer iddetallepresupuesto, int cantidad, float valorunitario, float total, Date fechacreacion, String usuariocreacion, boolean activo) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
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

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public String getNombrearchivo() {
        return nombrearchivo;
    }

    public void setNombrearchivo(String nombrearchivo) {
        this.nombrearchivo = nombrearchivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public Proyectos getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Proyectos idpresupuesto) {
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
        if (!(object instanceof Detalleproyecto)) {
            return false;
        }
        Detalleproyecto other = (Detalleproyecto) object;
        if ((this.iddetallepresupuesto == null && other.iddetallepresupuesto != null) || (this.iddetallepresupuesto != null && !this.iddetallepresupuesto.equals(other.iddetallepresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Detalleproyecto[ iddetallepresupuesto=" + iddetallepresupuesto + " ]";
    }
    
}
