package com.solec.web.tipo.gasto;

import com.solec.api.ejb.TipoGastoBeanLocal;
import com.solec.api.entity.Tipogasto;
import com.solec.web.utils.JsfUtil;
import com.solec.web.utils.SesionUsuarioMB;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "listaTipoGastoMB")
@ViewScoped
public class ListaTipoGastoMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaTipoGastoMB.class);

    @EJB
    private TipoGastoBeanLocal tipoGastoBean;

    private List<Tipogasto> listTipoGasto;

    @PostConstruct
    public void cargarDatos() {
        listTipoGasto = tipoGastoBean.ListTipoGasto();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/tipo_gasto/registro.xhtml");
    }

    public void detalle(Integer id) {
        JsfUtil.redirectTo("/tipo_gasto/detalle.xhtml?idtipocantidad=" + id);
    }

    public void eliminar(Integer id) throws IOException {
        String usuario = SesionUsuarioMB.getUserName();
        Tipogasto response = tipoGastoBean.deleteTipoGasto(id, usuario);
        if (response != null) {
            JsfUtil.addSuccessMessage("Se elimino el tipo de cantidad");
            cargarDatos();
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al elimnar");
    }

    public void onRowEdit(RowEditEvent event) {
        Object value = event.getObject();
        Tipogasto tipo = (Tipogasto) value;

        if (tipo != null) {
            Tipogasto tt = tipoGastoBean.updateTipoGasto(tipo);
            JsfUtil.addSuccessMessage("Se actualizo el tipo gasto");
            cargarDatos();
        } else {
            JsfUtil.addErrorMessage("Sucedio un error al actualizar el registro");
        }
    }

    /*Metodos getters y setters*/
    public List<Tipogasto> getListTipoGasto() {
        return listTipoGasto;
    }

    public void setListTipoGasto(List<Tipogasto> listTipoGasto) {
        this.listTipoGasto = listTipoGasto;
    }

}
