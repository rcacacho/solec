package com.solec.web.presupuesto;

import com.solec.api.ejb.CatalogoBeanLocal;
import com.solec.api.ejb.PresupuestoBeanLocal;
import com.solec.api.ejb.TipoCantidadBeanLocal;
import com.solec.api.ejb.TipoGastoBeanLocal;
import com.solec.api.entity.Detallepresupuesto;
import com.solec.api.entity.Presupuesto;
import com.solec.api.entity.Tipocantidad;
import com.solec.api.entity.Tipogasto;
import com.solec.api.enums.ConfiguracionEnum;
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
    @Resource(lookup = "jdbc/solec")
    private DataSource dataSource;

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
        tipoGasto = new Tipogasto();
        tipoCantidad = new Tipocantidad();
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
        String ubicacionArchivo = catalogoBeanLocal.findConfiguracionByParametro(ConfiguracionEnum.CARPETA_ARCHIVOS.getParametro()).getValor();
        String nombreArchivo = event.getFile().getFileName();
        //archivo.setReferencia(JsfUtil.quitarExtension(nombreArchivo));
        //nombreArchivo = JsfUtil.armarNombre(nombreArchivo, "BitSol_constancia_" + idSolicitudConstancia);
        try {
            FileUtil.guardarArchivo(event.getFile().getInputstream(), nombreArchivo, ubicacionArchivo);
            detalle.setDirectorio(ubicacionArchivo);
            detalle.setNombrearchivo(nombreArchivo);
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
    
        public StreamedContent downloadFile(Detallepresupuesto archivo) {
        return FileUtil.getStreamedContent(archivo.getDirectorio(), archivo.getNombrearchivo());
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
