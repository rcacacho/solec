package com.solec.web.usuario;

import com.solec.api.ejb.UsuarioBeanLocal;
import com.solec.api.entity.Usuarios;
import com.solec.web.utils.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "detalleUsuarioMB")
@ViewScoped
public class DetalleUsuarioMB implements Serializable {
    private static final Logger log = Logger.getLogger(DetalleUsuarioMB.class);

    @EJB
    private UsuarioBeanLocal usuarioBean;

    private Integer idusuario;
    private Usuarios usuario;

    public void cargarDatos() {
        usuario = usuarioBean.findUsuario(idusuario);
    }

    public void regresar() {
        JsfUtil.redirectTo("/usuario/lista.xhtml");
    }
    
    /*Metodos getters y setteres*/
    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
}
