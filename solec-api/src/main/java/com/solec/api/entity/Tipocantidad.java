package com.solec.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "tipocantidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocantidad.findAll", query = "SELECT t FROM Tipocantidad t"),
    @NamedQuery(name = "Tipocantidad.findByIdtipocantidad", query = "SELECT t FROM Tipocantidad t WHERE t.idtipocantidad = :idtipocantidad"),
    @NamedQuery(name = "Tipocantidad.findByNombres", query = "SELECT t FROM Tipocantidad t WHERE t.nombres = :nombres"),
    @NamedQuery(name = "Tipocantidad.findByDescripcion", query = "SELECT t FROM Tipocantidad t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tipocantidad.findByFechacreacion", query = "SELECT t FROM Tipocantidad t WHERE t.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Tipocantidad.findByUsuariocreacion", query = "SELECT t FROM Tipocantidad t WHERE t.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Tipocantidad.findByFechaeliminacion", query = "SELECT t FROM Tipocantidad t WHERE t.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Tipocantidad.findByUsuarioeliminacion", query = "SELECT t FROM Tipocantidad t WHERE t.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Tipocantidad.findByActivo", query = "SELECT t FROM Tipocantidad t WHERE t.activo = :activo")})
public class Tipocantidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipocantidad")
    private Integer idtipocantidad;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombres")
    private String nombres;
    
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    
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
    
    @OneToMany(mappedBy = "idtipocantidad", fetch = FetchType.LAZY)
    private List<Detalleproyecto> detalleproyectoList;

    public Tipocantidad() {
    }

    public Tipocantidad(Integer idtipocantidad) {
        this.idtipocantidad = idtipocantidad;
    }

    public Tipocantidad(Integer idtipocantidad, String nombres, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idtipocantidad = idtipocantidad;
        this.nombres = nombres;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdtipocantidad() {
        return idtipocantidad;
    }

    public void setIdtipocantidad(Integer idtipocantidad) {
        this.idtipocantidad = idtipocantidad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public List<Detalleproyecto> getDetalleproyectoList() {
        return detalleproyectoList;
    }

    public void setDetalleproyectoList(List<Detalleproyecto> detalleproyectoList) {
        this.detalleproyectoList = detalleproyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipocantidad != null ? idtipocantidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocantidad)) {
            return false;
        }
        Tipocantidad other = (Tipocantidad) object;
        if ((this.idtipocantidad == null && other.idtipocantidad != null) || (this.idtipocantidad != null && !this.idtipocantidad.equals(other.idtipocantidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Tipocantidad[ idtipocantidad=" + idtipocantidad + " ]";
    }
    
}
