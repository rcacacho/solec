package com.solec.web.presupuesto;

import com.solec.api.ejb.CatalogoBeanLocal;
import com.solec.api.ejb.PresupuestoBeanLocal;
import com.solec.api.entity.Detallepresupuesto;
import com.solec.api.entity.Presupuesto;
import com.solec.api.entity.Tipocantidad;
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
    @EJB
    private CatalogoBeanLocal catalogoBeanLocal;

    private Integer idpresupuesto;
    private Presupuesto presupuesto;
    private List<Tipogasto> listTipoGasto;
    private List<Tipocantidad> listTipoCantidad;
    private Tipogasto tipoGastoSelected;
    private Tipocantidad tipoCantidadSelected;
    private Detallepresupuesto detalle;
    private List<Detallepresupuesto> listDetalle;

    public DetallePresupuestoMB() {
        detalle = new Detallepresupuesto();
    }

    public void cargarDatos() {
        if (presupuesto == null) {
            presupuesto = presupuestoBean.findPresupuesto(idpresupuesto);
            listTipoGasto = catalogoBeanLocal.ListTipoGasto();
            listTipoCantidad = catalogoBeanLocal.ListTipoCantidad();
            listDetalle = presupuestoBean.ListDetallePresupuestoByIdPresupuesto(idpresupuesto);
        }
    }

    public void regresar() {
        JsfUtil.redirectTo("/presupuesto/lista.xhtml");
    }

    public void calcularTotal() {
        if (detalle.getCantidad() == 0 || detalle.getValorunitario() == 0) {
            JsfUtil.addErrorMessage("Debe ingresar una valor y una cantidad");
            return;
        }

        detalle.setTotal(detalle.getCantidad() * detalle.getValorunitario());
    }

    public void saveDetalle() throws IOException {
        detalle.setIdpresupuesto(presupuesto);
        detalle.setIdtipocantidad(tipoCantidadSelected);
        detalle.setIdtipogasto(tipoGastoSelected);
        detalle.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Detallepresupuesto det = presupuestoBean.saveDetallePresupuesto(detalle);
        if (det != null) {
            JsfUtil.addSuccessMessage("Registro almacenado exitosamente");
        }

        Double total = presupuestoBean.finDetallePresupuestoSumByIdPresupuesto(presupuesto.getIdpresupuesto());
        presupuesto.setTotalpresupuesto(total);
        Presupuesto pp = presupuestoBean.updatePresupuesto(presupuesto);

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

    public List<Tipogasto> getListTipoGasto() {
        return listTipoGasto;
    }

    public void setListTipoGasto(List<Tipogasto> listTipoGasto) {
        this.listTipoGasto = listTipoGasto;
    }

    public List<Tipocantidad> getListTipoCantidad() {
        return listTipoCantidad;
    }

    public void setListTipoCantidad(List<Tipocantidad> listTipoCantidad) {
        this.listTipoCantidad = listTipoCantidad;
    }

    public Tipogasto getTipoGastoSelected() {
        return tipoGastoSelected;
    }

    public void setTipoGastoSelected(Tipogasto tipoGastoSelected) {
        this.tipoGastoSelected = tipoGastoSelected;
    }

    public Tipocantidad getTipoCantidadSelected() {
        return tipoCantidadSelected;
    }

    public void setTipoCantidadSelected(Tipocantidad tipoCantidadSelected) {
        this.tipoCantidadSelected = tipoCantidadSelected;
    }

    public Detallepresupuesto getDetalle() {
        return detalle;
    }

    public void setDetalle(Detallepresupuesto detalle) {
        this.detalle = detalle;
    }

    public List<Detallepresupuesto> getListDetalle() {
        return listDetalle;
    }

    public void setListDetalle(List<Detallepresupuesto> listDetalle) {
        this.listDetalle = listDetalle;
    }

}
