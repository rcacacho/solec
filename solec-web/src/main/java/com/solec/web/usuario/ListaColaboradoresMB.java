package com.solec.web.usuario;

import com.solec.api.ejb.CatalogoBeanLocal;
import com.solec.api.entity.Usuarios;
import com.solec.web.utils.JsfUtil;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author elfo_
 */
public class ListaColaboradoresMB {

    private static final Logger log = Logger.getLogger(ListaColaboradoresMB.class);

//    @EJB
//    private UsuarioBeanLocal usuarioBeanLocal;
    @EJB
    private CatalogoBeanLocal catalogoBeanLocal;

    private List<Usuarios> listUsuario;
    private Usuarios selectedUsuario;
    private Usuarios usuarioAsignacion;

    public ListaColaboradoresMB() {
    }

    public void cargarDatos() {
       // listUsuario = usuarioBeanLocal.listaUsuarios();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/usuario/registro.xhtml");
    }

    public void detalle(Integer id) {
        JsfUtil.redirectTo("/usuario/detalle.xhtml?idusuario=" + id);
    }

    public void reinicioPassword(Usuarios usu) {
        selectedUsuario = usu;
        RequestContext.getCurrentInstance().execute("PF('dlgReinicio').show()");
    }

    public void reinicioUsuario() throws IOException {
        String contra = md5(selectedUsuario.getPassword());
        selectedUsuario.setPassword(contra);
        //selectedUsuario.setUsuariomodificacion(SesionUsuarioMB.getUserName());
        //Usuarios responseVerificacion = usuarioBeanLocal.reinicioPassword(selectedUsuario);
//        if (responseVerificacion != null) {
//            JsfUtil.addSuccessMessage("Se reinicio la contraseña exitosamente");
//            selectedUsuario = null;
//            return;
//        }
    }

    public String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrarDialog() {
        selectedUsuario = null;
        RequestContext.getCurrentInstance().execute("PF('dlgReinicio').hide()");
    }

    public void eliminarUsuario(Usuarios us) throws IOException {
        //us.setFechacrecion(new Date());
        //us.setUsuariomodificacion(SesionUsuarioMB.getUserName());
        us.setActivo(false);
//        Usuarios response = usuarioBeanLocal.updateUsuario(us);
//        if (response != null) {
//            JsfUtil.addSuccessMessage("Usuario eliminado exitosamente");
//        } else {
//            JsfUtil.addErrorMessage("Ocurrio un error al eliminar el usuario");
//        }

        cargarDatos();
    }


    /*Metodos getters y setteres*/
    public List<Usuarios> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuarios> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public Usuarios getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuarios selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public Usuarios getUsuarioAsignacion() {
        return usuarioAsignacion;
    }

    public void setUsuarioAsignacion(Usuarios usuarioAsignacion) {
        this.usuarioAsignacion = usuarioAsignacion;
    }

}
