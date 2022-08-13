package com.solec.web.tipo.cantidad;

import com.solec.api.ejb.TipoCantidadBeanLocal;
import com.solec.api.entity.Tipocantidad;
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
@ManagedBean(name = "listaTipoCantidadMB")
@ViewScoped
public class ListaTipoCantidadMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaTipoCantidadMB.class);

    @EJB
    private TipoCantidadBeanLocal tipoCantidadBean;

    private List<Tipocantidad> listTipoCantidad;

    @PostConstruct
    public void cargarDatos() {
        listTipoCantidad = tipoCantidadBean.ListTipoCantidad();
    }
    
      public void linkRegistro() {
        JsfUtil.redirectTo("/tipo_cantidad/registro.xhtml");
    }

    public void detalle(Integer id) {
        JsfUtil.redirectTo("/tipo_cantidad/detalle.xhtml?idtipocantidad=" + id);
    }

    public void eliminar(Integer id) throws IOException {
        String usuario = SesionUsuarioMB.getUserName();
        Tipocantidad response = tipoCantidadBean.deleteTipoCantidad(id, usuario);
        if (response != null) {
            JsfUtil.addSuccessMessage("Se elimino el tipo de cantidad");
            cargarDatos();
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al elimnar");
    }

    public void onRowEdit(RowEditEvent event) {
        Object value = event.getObject();
        Tipocantidad tipo = (Tipocantidad) value;

        if (tipo != null) {
            Tipocantidad tt = tipoCantidadBean.updateTipoCantidad(tipo);
            JsfUtil.addSuccessMessage("Se actualizo el tipo de cantidad");
            cargarDatos();
        } else {
            JsfUtil.addErrorMessage("Sucedio un error al actualizar el registro");
        }
    }

    /*Metodos getters y setters*/
    public List<Tipocantidad> getListTipoCantidad() {
        return listTipoCantidad;
    }

    public void setListTipoCantidad(List<Tipocantidad> listTipoCantidad) {
        this.listTipoCantidad = listTipoCantidad;
    }

}
