package com.solec.bussines.imp;

import com.solec.api.ejb.TipoCantidadBeanLocal;
import com.solec.api.entity.Tipocantidad;
import java.util.Date;
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
 * @author elfo_
 */
@Singleton
public class TipoCantidadBean implements TipoCantidadBeanLocal {

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
    public List<Tipocantidad> ListTipoCantidad() {
        List<Tipocantidad> lst = em.createQuery("SELECT qj FROM Tipocantidad qj where qj.activo = true ", Tipocantidad.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Tipocantidad saveTipoCantidad(Tipocantidad tipoCantidad) {
        try {
            tipoCantidad.setActivo(true);
            tipoCantidad.setFechacreacion(new Date());
            em.persist(tipoCantidad);
            em.flush();
            return (tipoCantidad);
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
    public Tipocantidad updateTipoCantidad(Tipocantidad tipoCantidad) {
        try {
            em.merge(tipoCantidad);
            em.flush();
            return (tipoCantidad);
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
    public Tipocantidad deleteTipoCantidad(Integer idtipocantidad, String usuario) {
          if (idtipocantidad == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            Tipocantidad toUpdate = em.find(Tipocantidad.class, idtipocantidad);
            toUpdate.setUsuarioeliminacion(usuario);
            toUpdate.setFechaeliminacion(new Date());
            toUpdate.setActivo(false);
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

}
