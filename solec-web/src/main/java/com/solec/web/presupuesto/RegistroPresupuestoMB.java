package com.solec.web.presupuesto;

import com.solec.api.ejb.PresupuestoBeanLocal;
import com.solec.api.entity.Presupuesto;
import com.solec.web.utils.JsfUtil;
import com.solec.web.utils.SesionUsuarioMB;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "registroPresupuestoMB")
@ViewScoped
public class RegistroPresupuestoMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroPresupuestoMB.class);

    @EJB
    private PresupuestoBeanLocal presupuestoBean;

    private Presupuesto presupuesto;

    public RegistroPresupuestoMB() {
        presupuesto = new Presupuesto();
    }

    public void regresar() {
        JsfUtil.redirectTo("/presupuesto/lista.xhtml");
    }

    public void savePresupuesto() throws IOException {
        presupuesto.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Presupuesto response = presupuestoBean.savePresupuesto(presupuesto);
        if (response != null) {
            JsfUtil.addSuccessMessage("Presupuesto registrado exitosamente");
            presupuesto = null;
            return;
        }
    }

    /*Metodos getters y setters*/
    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

}
