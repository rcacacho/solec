package com.solec.web.presupuesto;

import com.solec.api.ejb.CatalogoBeanLocal;
import com.solec.api.ejb.PresupuestoBeanLocal;
import com.solec.api.entity.Presupuesto;
import com.solec.web.utils.JsfUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "presupuestoBuzonMB")
@ViewScoped
public class PresupuestoBuzonMB implements Serializable {

    private static final Logger log = Logger.getLogger(PresupuestoBuzonMB.class);

    @EJB
    private PresupuestoBeanLocal presupuestoBean;
    @EJB
    private CatalogoBeanLocal catalogoBean;

    private List<Presupuesto> listPresupuesto;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;

    @PostConstruct
    public void init() {
        listPresupuesto = presupuestoBean.ListPresupuestos();
    }

    public void buscar() {

    }

    public void limpiar() {
        nombre = null;
        fechaFin = null;
        fechaInicio = null;
    }

    public void detallePresupuesto(Integer id) {
        JsfUtil.redirectTo("/presupuesto/detalle.xhtml?idpresupuesto=" + id);
    }

    /*Metodos getters y setters*/
    public List<Presupuesto> getListPresupuesto() {
        return listPresupuesto;
    }

    public void setListPresupuesto(List<Presupuesto> listPresupuesto) {
        this.listPresupuesto = listPresupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

}
