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
 * @author rcacacho
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p"),
    @NamedQuery(name = "Proyectos.findByIdpresupuesto", query = "SELECT p FROM Proyectos p WHERE p.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Proyectos.findByNombre", query = "SELECT p FROM Proyectos p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Proyectos.findByDireccion", query = "SELECT p FROM Proyectos p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Proyectos.findByFechacreacion", query = "SELECT p FROM Proyectos p WHERE p.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Proyectos.findByUsuariocreacion", query = "SELECT p FROM Proyectos p WHERE p.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Proyectos.findByActivo", query = "SELECT p FROM Proyectos p WHERE p.activo = :activo")})
public class Proyectos implements Serializable {

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

    @Column(name = "totalproyecto")
    private Double totalproyecto;

    @Column(name = "totalgastado")
    private Double totalgastado;

    @Column(name = "totalrecibido")
    private Double totalrecibido;

    @Column(name = "saldo")
    private Double saldo;

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

    @Size(max = 1000)
    @Column(name = "motivoeliminacion")
    private String motivoeliminacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpresupuesto", fetch = FetchType.LAZY)
    private List<Detalleproyecto> detalleproyectoList;

    public Proyectos() {
    }

    public Proyectos(Integer idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public Proyectos(Integer idpresupuesto, String nombre, Date fechacreacion, String usuariocreacion, boolean activo) {
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

    public Double getTotalproyecto() {
        return totalproyecto;
    }

    public void setTotalproyecto(Double totalproyecto) {
        this.totalproyecto = totalproyecto;
    }

    public Double getTotalgastado() {
        return totalgastado;
    }

    public void setTotalgastado(Double totalgastado) {
        this.totalgastado = totalgastado;
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

    public Double getTotalrecibido() {
        return totalrecibido;
    }

    public void setTotalrecibido(Double totalrecibido) {
        this.totalrecibido = totalrecibido;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @XmlTransient
    public List<Detalleproyecto> getDetalleproyectoList() {
        return detalleproyectoList;
    }

    public void setDetalleproyectoList(List<Detalleproyecto> detalleproyectoList) {
        this.detalleproyectoList = detalleproyectoList;
    }

    public String getMotivoeliminacion() {
        return motivoeliminacion;
    }

    public void setMotivoeliminacion(String motivoeliminacion) {
        this.motivoeliminacion = motivoeliminacion;
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
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.idpresupuesto == null && other.idpresupuesto != null) || (this.idpresupuesto != null && !this.idpresupuesto.equals(other.idpresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.solec.api.entity.Proyectos[ idpresupuesto=" + idpresupuesto + " ]";
    }

}
