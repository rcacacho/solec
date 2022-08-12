package com.solec.bussines.imp;

import com.solec.api.ejb.LoginBeanLocal;
import com.solec.api.entity.Usuarios;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author elfo_
 */
@Singleton
public class LoginBean implements LoginBeanLocal {
    
    private static final Logger log = Logger.getLogger(LoginBean.class);

    @PersistenceContext(unitName = "SolecPU")
    EntityManager em;

    @Resource
    private EJBContext context;

    @Override
    public Usuarios verificarUsuario(String usuario, String password) {
        List<Usuarios> lst = em.createQuery("SELECT us FROM Usuarios us WHERE us.usuario =:usuario and us.password =:password and us.activo = true ", Usuarios.class)
                .setParameter("usuario", usuario)
                .setParameter("password", password)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public String findUsuario(String usuario) {
         List<Usuarios> lst = em.createQuery("SELECT us FROM Usuarios us WHERE us.usuario =:usuario ", Usuarios.class)
                .setParameter("usuario", usuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0).getUsuario();
    }

}
