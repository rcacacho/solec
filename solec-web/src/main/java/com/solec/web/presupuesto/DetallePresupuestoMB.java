package com.solec.web.presupuesto;

import com.solec.api.ejb.PresupuestoBeanLocal;
import com.solec.api.entity.Presupuesto;
import com.solec.web.utils.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "detallePresupuestoMB")
@ViewScoped
public class DetallePresupuestoMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetallePresupuestoMB.class);

    @EJB
    private PresupuestoBeanLocal presupuestoBean;

    private Integer idpresupuesto;
    private Presupuesto presupuesto;

    public void cargarDatos() {
        presupuesto = presupuestoBean.findPresupuesto(idpresupuesto);
    }

    public void regresar() {
        JsfUtil.redirectTo("/configuracion/lista.xhtml");
    }

    /*Metodos getters y setters*/
    public Integer getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Integer idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

}
