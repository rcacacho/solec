package com.solec.web.presupuesto;

import com.solec.api.ejb.CatalogoBeanLocal;
import com.solec.web.utils.JsfUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import com.solec.api.ejb.ProyectoBeanLocal;
import com.solec.api.entity.Proyectos;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "presupuestoBuzonMB")
@ViewScoped
public class PresupuestoBuzonMB implements Serializable {

    private static final Logger log = Logger.getLogger(PresupuestoBuzonMB.class);

    @EJB
    private ProyectoBeanLocal presupuestoBean;

    private List<Proyectos> listPresupuesto;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;

    @PostConstruct
    public void init() {
        listPresupuesto = presupuestoBean.ListProyectos();
    }

    public void buscar() {

    }

    public void limpiar() {
        nombre = null;
        fechaFin = null;
        fechaInicio = null;
    }

    public void detallePresupuesto(Integer id) {
        JsfUtil.redirectTo("/proyecto/detalle.xhtml?idpresupuesto=" + id);
    }

    public void onRowEdit(RowEditEvent event) {
        Object value = event.getObject();
        Proyectos tipo = (Proyectos) value;

        if (tipo != null) {
            Proyectos tt = presupuestoBean.updateProyecto(tipo);
            JsfUtil.addSuccessMessage("Se actualizo el proyecto exitosamente");
            listPresupuesto = presupuestoBean.ListProyectos();
        } else {
            JsfUtil.addErrorMessage("Sucedio un error al actualizar el registro");
        }
    }

    /*Metodos getters y setters*/
    public List<Proyectos> getListPresupuesto() {
        return listPresupuesto;
    }

    public void setListPresupuesto(List<Proyectos> listPresupuesto) {
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
