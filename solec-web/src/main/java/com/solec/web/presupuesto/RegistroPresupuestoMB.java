package com.solec.web.presupuesto;

import com.solec.web.utils.JsfUtil;
import com.solec.web.utils.SesionUsuarioMB;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import com.solec.api.ejb.ProyectoBeanLocal;
import com.solec.api.entity.Proyectos;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "registroPresupuestoMB")
@ViewScoped
public class RegistroPresupuestoMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroPresupuestoMB.class);

    @EJB
    private ProyectoBeanLocal presupuestoBean;

    private Proyectos presupuesto;

    public RegistroPresupuestoMB() {
        presupuesto = new Proyectos();
    }

    public void regresar() {
        JsfUtil.redirectTo("/proyecto/lista.xhtml");
    }

    public void savePresupuesto() throws IOException {
        presupuesto.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Proyectos response = presupuestoBean.saveProyectos(presupuesto);
        if (response != null) {
            JsfUtil.addSuccessMessage("Proyecto registrado exitosamente");
            presupuesto = null;
            return;
        }
        JsfUtil.redirectTo("/proyecto/lista.xhtml");
    }

    /*Metodos getters y setters*/
    public Proyectos getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Proyectos presupuesto) {
        this.presupuesto = presupuesto;
    }

}
