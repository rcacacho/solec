package com.solec.web.presupuesto;

import com.solec.api.ejb.CatalogoBeanLocal;
import com.solec.api.ejb.TipoCantidadBeanLocal;
import com.solec.api.ejb.TipoGastoBeanLocal;
import com.solec.api.entity.Tipocantidad;
import com.solec.api.entity.Tipogasto;
import com.solec.web.utils.FileUtil;
import com.solec.web.utils.JasperUtil;
import com.solec.web.utils.JsfUtil;
import com.solec.web.utils.ReporteJasper;
import com.solec.web.utils.SesionUsuarioMB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import com.solec.api.ejb.ProyectoBeanLocal;
import com.solec.api.entity.Detalleproyecto;
import com.solec.api.entity.Proyectos;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "detallePresupuestoMB")
@ViewScoped
public class DetallePresupuestoMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetallePresupuestoMB.class);

    @EJB
    private ProyectoBeanLocal presupuestoBean;
    @EJB
    private CatalogoBeanLocal catalogoBeanLocal;
    @EJB
    private TipoCantidadBeanLocal tipoCantidadBean;
    @EJB
    private TipoGastoBeanLocal tipoGastoBean;
    @Resource(lookup = "jdbc/solec")
    private DataSource dataSource;

    private Integer idpresupuesto;
    private Proyectos presupuesto;
    private List<Tipogasto> listTipoGasto;
    private List<Tipocantidad> listTipoCantidad;
    private Tipogasto tipoGastoSelected;
    private Tipocantidad tipoCantidadSelected;
    private Detalleproyecto detalle;
    private List<Detalleproyecto> listDetalle;
    private Tipogasto tipoGasto;
    private Tipocantidad tipoCantidad;
    private UploadedFile archivo;
    private String motivoEliminacion;
    private Detalleproyecto detalleSelected;

    public DetallePresupuestoMB() {
        detalle = new Detalleproyecto();
        tipoGasto = new Tipogasto();
        tipoCantidad = new Tipocantidad();
    }

    public void cargarDatos() {
        if (presupuesto == null) {
            presupuesto = presupuestoBean.findProyecto(idpresupuesto);
            listTipoGasto = catalogoBeanLocal.ListTipoGasto();
            listTipoCantidad = catalogoBeanLocal.ListTipoCantidad();
            listDetalle = presupuestoBean.ListDetalleProyectoByIdPresupuesto(idpresupuesto);
        }
    }

    public void regresar() {
        JsfUtil.redirectTo("/proyecto/lista.xhtml");
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
        Detalleproyecto det = presupuestoBean.saveDetalleProyecto(detalle);
        if (det != null) {
            JsfUtil.addSuccessMessage("Registro almacenado exitosamente");
        }

        Double total = presupuestoBean.finDetalleProyectoSumByIdProyecto(presupuesto.getIdpresupuesto());
        presupuesto.setTotalpresupuesto(total);
        Proyectos pp = presupuestoBean.updateProyecto(presupuesto);
        detalle = null;
        tipoCantidadSelected = null;
        detalle = new Detalleproyecto();
        listDetalle = presupuestoBean.ListDetalleProyectoByIdPresupuesto(idpresupuesto);
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
        tipoCantidad.setDescripcion(tipoCantidad.getNombres());
        Tipocantidad tip = tipoCantidadBean.saveTipoCantidad(tipoCantidad);
        if (tip != null) {
            JsfUtil.addSuccessMessage("Unidad registrada exitosamente");
        }
        listTipoCantidad = catalogoBeanLocal.ListTipoCantidad();
    }

    public void saveValor() throws IOException {
        tipoGasto.setUsuariocreacion(SesionUsuarioMB.getUserName());
        tipoGasto.setDescripciongasto(tipoGasto.getNombregasto());
        Tipogasto tip = tipoGastoBean.saveTipoGasto(tipoGasto);
        if (tip != null) {
            JsfUtil.addSuccessMessage("Valor registrada exitosamente");
        }
        listTipoGasto = catalogoBeanLocal.ListTipoGasto();
    }

    public void handleFileUpload(FileUploadEvent event) {
        String ubicacionArchivo = "\\opt\\image\\";
        String nombreArchivo = event.getFile().getFileName();
        detalle.setReferencianombre(JsfUtil.quitarExtension(nombreArchivo));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         String nombreArchivo2 = nombreArchivo + "_" + sdf.format(new Date());

        try {
            FileUtil.guardarArchivo(event.getFile().getInputstream(), nombreArchivo, ubicacionArchivo);
            detalle.setDirectorio(ubicacionArchivo);
            detalle.setNombrearchivo(nombreArchivo2);
            JsfUtil.addSuccessMessage("Archivo cargado exitosamente");
        } catch (IOException ioe) {
            log.error(ioe.getLocalizedMessage());
            JsfUtil.addErrorMessage("Error al cargar el archivo");
        }
    }

    public StreamedContent generarPdf() {
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath = servletContext.getRealPath("/");
            String nombreReporte = "rptPresupuestado";
            String nombreArchivo = "Presupuesto.pdf";

            HashMap parametros = new HashMap();
            parametros.put("IMAGE", "logo.jpeg");
            parametros.put("DIRECTORIO", realPath + File.separator + "resources" + File.separator + "images" + File.separator);
            parametros.put("USUARIO", SesionUsuarioMB.getUserName());
            parametros.put("ID_PRESUPUESTO", idpresupuesto);
            parametros.put("TOTAL", presupuesto.getTotalpresupuesto());

            ReporteJasper reporteJasper = JasperUtil.jasperReportPDF(nombreReporte, nombreArchivo, parametros, dataSource);
            StreamedContent streamedContent;
            FileInputStream stream = new FileInputStream(realPath + "resources/reports/" + reporteJasper.getFileName());
            streamedContent = new DefaultStreamedContent(stream, "application/pdf", reporteJasper.getFileName());
            return streamedContent;
        } catch (Exception ex) {
            log.error(ex);
            JsfUtil.addErrorMessage("Ocurrio un error al generar el pdf del reporte");
        }
        return null;
    }

    public StreamedContent downloadFile(Detalleproyecto archivo) {
        return FileUtil.getStreamedContent(archivo.getDirectorio(), archivo.getNombrearchivo());
    }

    public void eliminarDetalle() throws IOException {
        Detalleproyecto response = presupuestoBean.eliminarDetalleProyecto(detalleSelected.getIddetallepresupuesto(), SesionUsuarioMB.getUserName(), motivoEliminacion);
        if (response != null) {
            listDetalle = presupuestoBean.ListDetalleProyectoByIdPresupuesto(idpresupuesto);
            JsfUtil.addSuccessMessage("Se elimino el registro exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al elimnar");
    }

    public void dialogEliminar(Detalleproyecto det) {
        RequestContext.getCurrentInstance().execute("PF('dlgPago').show()");
        motivoEliminacion = null;
        detalleSelected = det;
    }

    public void cerrarDialog() {
        RequestContext.getCurrentInstance().execute("PF('dlgPago').hide()");
    }

    public void onRowEdit(RowEditEvent event) {
        Object value = event.getObject();
        Detalleproyecto tipo = (Detalleproyecto) value;

        if (tipo != null) {
            tipo.setTotal(tipo.getCantidad() * tipo.getValorunitario());
            Detalleproyecto tt = presupuestoBean.updateDetalleProyecto(tipo);
            JsfUtil.addSuccessMessage("Se actualizo exitosamente");
            listDetalle = presupuestoBean.ListDetalleProyectoByIdPresupuesto(idpresupuesto);
        } else {
            JsfUtil.addErrorMessage("Sucedio un error al actualizar el registro");
        }
    }

    /*Metodos getters y setters*/
    public Integer getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Integer idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public Proyectos getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Proyectos presupuesto) {
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

    public Detalleproyecto getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalleproyecto detalle) {
        this.detalle = detalle;
    }

    public List<Detalleproyecto> getListDetalle() {
        return listDetalle;
    }

    public void setListDetalle(List<Detalleproyecto> listDetalle) {
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

    public String getMotivoEliminacion() {
        return motivoEliminacion;
    }

    public void setMotivoEliminacion(String motivoEliminacion) {
        this.motivoEliminacion = motivoEliminacion;
    }

    public Detalleproyecto getDetalleSelected() {
        return detalleSelected;
    }

    public void setDetalleSelected(Detalleproyecto detalleSelected) {
        this.detalleSelected = detalleSelected;
    }

}
