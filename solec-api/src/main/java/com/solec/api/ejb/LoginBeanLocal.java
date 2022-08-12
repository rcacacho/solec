package com.solec.api.ejb;

import com.solec.api.entity.Usuarios;

/**
 *
 * @author elfo_
 */
public interface LoginBeanLocal {
    
    Usuarios verificarUsuario(String usuario, String password);

    String findUsuario(String usuario);
    
}
