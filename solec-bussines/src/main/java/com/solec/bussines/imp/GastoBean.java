package com.solec.bussines.imp;

import com.solec.api.ejb.GastoBeanLocal;
import com.solec.api.entity.Gasto;
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
public class GastoBean implements GastoBeanLocal {

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
    public List<Gasto> ListGastos() {
        List<Gasto> lst = em.createQuery("SELECT qj FROM Gasto qj where qj.activo = true ", Gasto.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Gasto saveGasto(Gasto gasto) {
     try {
            gasto.setActivo(true);
            gasto.setFechacreacion(new Date());
            em.persist(gasto);
            em.flush();
            return (gasto);
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
    public Gasto updateGasto(Gasto gasto) {
       try {
            em.merge(gasto);
            em.flush();
            return (gasto);
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
    public List<Gasto> ListGastoByNombre(String nombre) {
        if (nombre == null) {
            return null;
        }

        List<Gasto> lst = em.createQuery("SELECT col FROM Gasto col WHERE col.nombre like :nombre ", Gasto.class)
                .setParameter("nombre", '%' + nombre + '%')
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst;
    }

    @Override
    public Gasto findGasto(Integer idgasto) {
         if (idgasto == null) {
            return null;
        }

        List<Gasto> lst = em.createQuery("SELECT col FROM Gasto col WHERE col.idgasto =:idgasto ", Gasto.class)
                .setParameter("idgasto", idgasto)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst.get(0);
    }

}
