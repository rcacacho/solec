package com.solec.bussines.imp;

import com.solec.api.ejb.TipoGastoBeanLocal;
import com.solec.api.entity.Tipocantidad;
import com.solec.api.entity.Tipogasto;
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
public class TipoGastoBean implements TipoGastoBeanLocal {

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
    public List<Tipogasto> ListTipoGasto() {
        List<Tipogasto> lst = em.createQuery("SELECT qj FROM Tipogasto qj where qj.activo = true ", Tipogasto.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Tipogasto saveTipoGasto(Tipogasto tipoGasto) {
        try {
            tipoGasto.setActivo(true);
            tipoGasto.setFechacreacion(new Date());
            em.persist(tipoGasto);
            em.flush();
            return (tipoGasto);
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
    public Tipogasto updateTipoGasto(Tipogasto tipoGasto) {
        try {
            em.merge(tipoGasto);
            em.flush();
            return (tipoGasto);
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
    public Tipogasto deleteTipoGasto(Integer idtipogasto, String usuario) {
        if (idtipogasto == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            Tipogasto toUpdate = em.find(Tipogasto.class, idtipogasto);
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
