package com.solec.api.ejb;

import com.solec.api.entity.Usuarios;
import java.util.List;

/**
 *
 * @author elfo_
 */
public interface UsuarioBeanLocal {
    
    List<Usuarios> listaUsuarios();

    Usuarios saveUsuario(Usuarios usuario);

    Usuarios findUsuario(Integer idusuario);

    Usuarios findUsuario(String usuario);

    Usuarios reinicioPassword(Usuarios usuario);

    Usuarios updateUsuario(Usuarios usuario);    
}
