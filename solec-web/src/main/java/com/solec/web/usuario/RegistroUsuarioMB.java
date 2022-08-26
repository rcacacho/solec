package com.solec.web.usuario;

import com.solec.api.ejb.UsuarioBeanLocal;
import com.solec.api.entity.Usuarios;
import com.solec.web.utils.JsfUtil;
import com.solec.web.utils.SesionUsuarioMB;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "registroUsuarioMB")
@ViewScoped
public class RegistroUsuarioMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroUsuarioMB.class);

    @EJB
    private UsuarioBeanLocal usuarioBean;

    private Usuarios usuario;

    public RegistroUsuarioMB() {
        usuario = new Usuarios();
    }

    public void saveUsuario() throws IOException {
        usuario.setUsuariocreacion(SesionUsuarioMB.getUserName());
        Usuarios response = usuarioBean.findUsuario(usuario.getUsuario());
        if (response != null) {
            JsfUtil.addErrorMessage("El usuario ya existe");
            return;
        }

        String contra = md5(usuario.getPassword());
        usuario.setPassword(contra);
        usuario.setUsuariocreacion(SesionUsuarioMB.getUserName());
//        if (usuario.getTipousuario().equals("admin")) {
//            usuario.setRoot(true);
//        } else {
//            usuario.setRoot(false);
//        }

        Usuarios responseVerificacion = usuarioBean.saveUsuario(usuario);
        if (responseVerificacion != null) {
            JsfUtil.addSuccessMessage("Usuario registrado exitosamente");
            usuario = null;
            return;
        }
    }

    public void regresar() {
        JsfUtil.redirectTo("/usuarios/lista.xhtml");
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

    /*Metodos getters y setters*/
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }



}
