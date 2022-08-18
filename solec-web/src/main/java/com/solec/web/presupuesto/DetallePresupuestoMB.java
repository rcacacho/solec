package com.solec.web.presupuesto;

import com.solec.api.ejb.CatalogoBeanLocal;
import com.solec.api.ejb.PresupuestoBeanLocal;
import com.solec.api.ejb.TipoCantidadBeanLocal;
import com.solec.api.ejb.TipoGastoBeanLocal;
import com.solec.api.entity.Detallepresupuesto;
import com.solec.api.entity.Presupuesto;
import com.solec.api.entity.Tipocantidad;
import com.solec.api.entity.Tipogasto;
import com.solec.web.utils.JsfUtil;
import com.solec.web.utils.SesionUsuarioMB;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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
    @EJB
    private TipoCantidadBeanLocal tipoCantidadBean;
    @EJB
    private TipoGastoBeanLocal tipoGastoBean;

    private Integer idpresupuesto;
    private Presupuesto presupuesto;
    private List<Tipogasto> listTipoGasto;
    private List<Tipocantidad> listTipoCantidad;
    private Tipogasto tipoGastoSelected;
    private Tipocantidad tipoCantidadSelected;
    private Detallepresupuesto detalle;
    private List<Detallepresupuesto> listDetalle;
    private Tipogasto tipoGasto;
    private Tipocantidad tipoCantidad;
    private UploadedFile archivo;

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

    public void cargarDialogValor() {
        tipoGasto = new Tipogasto();
        RequestContext.getCurrentInstance().execute("PF('dlgValor').show()");
    }

    public void cerrarDialogValor() {
        RequestContext.getCurrentInstance().execute("PF('dlgValor').hide()");
    }

    public void cargarDialogUnidad() {
        tipoCantidad = new Tipocantidad();
        RequestContext.getCurrentInstance().execute("PF('dlgUnidad').show()");
    }

    public void cerrarDialogUnidad() {
        RequestContext.getCurrentInstance().execute("PF('dlgUnidad').hide()");
    }

    public void saveUnidad() throws IOException {
        tipoCantidad.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Tipocantidad tip = tipoCantidadBean.saveTipoCantidad(tipoCantidad);
        if (tip != null) {
            JsfUtil.addSuccessMessage("Unidad registrada exitosamente");
        }
        listTipoCantidad = catalogoBeanLocal.ListTipoCantidad();
    }

    public void saveValor() throws IOException {
        tipoGasto.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Tipogasto tip = tipoGastoBean.saveTipoGasto(tipoGasto);
        if (tip != null) {
            JsfUtil.addSuccessMessage("Unidad registrada exitosamente");
        }
        listTipoGasto = catalogoBeanLocal.ListTipoGasto();
    }

    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        String fileName = uploadedFile.getFileName();
        String contentType = uploadedFile.getContentType();
        byte[] contents = uploadedFile.getContents(); // Or getInputStream()
        // ... Save it, now!
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

    public Tipogasto getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(Tipogasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public Tipocantidad getTipoCantidad() {
        return tipoCantidad;
    }

    public void setTipoCantidad(Tipocantidad tipoCantidad) {
        this.tipoCantidad = tipoCantidad;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

}
