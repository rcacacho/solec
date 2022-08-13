package com.solec.web.tipo.cantidad;

import com.solec.api.ejb.TipoCantidadBeanLocal;
import com.solec.api.entity.Tipocantidad;
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
@ManagedBean(name = "registroTipoCantidad")
@ViewScoped
public class RegistroTipoCantidad implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroTipoCantidad.class);

    @EJB
    private TipoCantidadBeanLocal tipoCantidadBean;

    private Tipocantidad tipoCantidad;

    public RegistroTipoCantidad() {
        tipoCantidad = new Tipocantidad();
    }

    public void regresar() {
        JsfUtil.redirectTo("/tipo_cantidad/lista.xhtml");
    }

    public void saveTipoCantidad() throws IOException {
        tipoCantidad.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Tipocantidad response = tipoCantidadBean.saveTipoCantidad(tipoCantidad);
        if (response != null) {
            JsfUtil.addSuccessMessage("Tipo cantidad registrado exitosamente");
            tipoCantidad = null;
            return;
        }
    }

    /*Metodos getters y setters*/
    public Tipocantidad getTipoCantidad() {
        return tipoCantidad;
    }

    public void setTipoCantidad(Tipocantidad tipoCantidad) {
        this.tipoCantidad = tipoCantidad;
    }

}
