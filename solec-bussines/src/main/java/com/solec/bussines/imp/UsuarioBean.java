package com.solec.bussines.imp;

import com.solec.api.ejb.UsuarioBeanLocal;
import com.solec.api.entity.Usuarios;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
@Singleton
public class UsuarioBean implements UsuarioBeanLocal {

    private static final Logger log = Logger.getLogger(PresupuestoBean.class);

    @PersistenceContext(unitName = "SolecPU")
    EntityManager em;

    @Resource
    private EJBContext context;

    private void processException(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

    private String getConstraintViolationExceptionAsString(ConstraintViolationException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error de validación:\n");
        for (ConstraintViolation c : ex.getConstraintViolations()) {
            sb.append(String.format("[bean: %s; field: %s; message: %s; value: %s]",
                    c.getRootBeanClass().getName(),
                    c.getPropertyPath().toString(),
                    c.getMessage(), c.getInvalidValue()));
        }
        return sb.toString();
    }

    @Override
    public List<Usuarios> listaUsuarios() {
        List<Usuarios> lst = em.createQuery("SELECT qj FROM Usuarios qj where qj.activo = true", Usuarios.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Usuarios saveUsuario(Usuarios usuario) {
        try {
            //usuario.setFechacreacion(new Date());
            usuario.setActivo(true);
            em.persist(usuario);
            em.flush();
            return (usuario);
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            context.setRollbackOnly();
            return null;
        }
    }

    @Override
    public Usuarios findUsuario(Integer idusuario) {
        List<Usuarios> lst = em.createQuery("SELECT us FROM Usuarios us WHERE us.idusuario =:idusuario ", Usuarios.class)
                .setParameter("idusuario", idusuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Usuarios findUsuario(String usuario) {
        List<Usuarios> lst = em.createQuery("SELECT us FROM Usuarios us WHERE us.usuario =:usuario ", Usuarios.class)
                .setParameter("usuario", usuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Usuarios reinicioPassword(Usuarios usuario) {
            if (usuario == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            Usuarios toUpdate = em.find(Usuarios.class, usuario.getIdusuario());

            toUpdate.setPassword(usuario.getPassword());
            //toUpdate.setUsuariomodificacion(usuario.getUsuariomodificacion());
            //toUpdate.setFechamodificacion(new Date());
            em.merge(toUpdate);

            return toUpdate;
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            return null;
        }
    }

    @Override
    public Usuarios updateUsuario(Usuarios usuario) {
            if (usuario == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            em.merge(usuario);

            return usuario;
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            return null;
        }
    }

}
