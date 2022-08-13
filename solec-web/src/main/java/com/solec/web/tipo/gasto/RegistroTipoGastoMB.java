package com.solec.web.tipo.gasto;

import com.solec.api.ejb.TipoGastoBeanLocal;
import com.solec.api.entity.Tipogasto;
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
@ManagedBean(name = "registroTipoGastoMB")
@ViewScoped
public class RegistroTipoGastoMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroTipoGastoMB.class);

    @EJB
    private TipoGastoBeanLocal tipoGastoBean;

    private Tipogasto tipoGasto;

    public RegistroTipoGastoMB() {
        tipoGasto = new Tipogasto();
    }

    public void regresar() {
        JsfUtil.redirectTo("/tipo_gasto/lista.xhtml");
    }

    public void saveTipoGasto() throws IOException {
        tipoGasto.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Tipogasto response = tipoGastoBean.saveTipoGasto(tipoGasto);
        if (response != null) {
            JsfUtil.addSuccessMessage("Tipo gasto registrado exitosamente");
            tipoGasto = null;
            return;
        }
    }

    /*Metodos gettets y setters*/
    public Tipogasto getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(Tipogasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

}
