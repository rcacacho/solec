package com.solec.bussines.imp;

import com.solec.api.ejb.PresupuestoBeanLocal;
import com.solec.api.entity.Detallepresupuesto;
import com.solec.api.entity.Presupuesto;
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
public class PresupuestoBean implements PresupuestoBeanLocal {

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
    public List<Presupuesto> ListPresupuestos() {
        List<Presupuesto> lst = em.createQuery("SELECT qj FROM Presupuesto qj where qj.activo = true ", Presupuesto.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Presupuesto savePresupuesto(Presupuesto presupuesto) {
        try {
            presupuesto.setActivo(true);
            presupuesto.setFechacreacion(new Date());
            em.persist(presupuesto);
            em.flush();
            return (presupuesto);
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
    public Presupuesto updatePresupuesto(Presupuesto presupuesto) {
        try {
            em.merge(presupuesto);
            em.flush();
            return (presupuesto);
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
    public List<Presupuesto> ListPresupuestoByNombre(String nombre) {
        if (nombre == null) {
            return null;
        }

        List<Presupuesto> lst = em.createQuery("SELECT col FROM Presupuesto col WHERE col.nombre like :nombre ", Presupuesto.class)
                .setParameter("nombre", '%' + nombre + '%')
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst;
    }

    @Override
    public Presupuesto findPresupuesto(Integer idpresupuesto) {
        if (idpresupuesto == null) {
            return null;
        }

        List<Presupuesto> lst = em.createQuery("SELECT col FROM Presupuesto col WHERE col.idpresupuesto =:idpresupuesto ", Presupuesto.class)
                .setParameter("idpresupuesto", idpresupuesto)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst.get(0);
    }

    @Override
    public Detallepresupuesto saveDetallePresupuesto(Detallepresupuesto detallePresupuesto) {
        try {
            detallePresupuesto.setActivo(true);
            detallePresupuesto.setFechacreacion(new Date());
            em.persist(detallePresupuesto);
            em.flush();
            return (detallePresupuesto);
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
    public List<Detallepresupuesto> ListDetallePresupuestoByIdPresupuesto(Integer idpresupuesto) {
        if (idpresupuesto == null) {
            return null;
        }

        List<Detallepresupuesto> lst = em.createQuery("SELECT col FROM Detallepresupuesto col WHERE col.idpresupuesto.idpresupuesto =:idpresupuesto ", Detallepresupuesto.class)
                .setParameter("idpresupuesto", idpresupuesto)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst;
    }

    @Override
    public Double finDetallePresupuestoSumByIdPresupuesto(Integer idpresupuesto) {
        if (idpresupuesto == null) {
            return null;
        }

        List<Double> lst = em.createQuery("SELECT sum (col.total) FROM Detallepresupuesto col WHERE col.idpresupuesto.idpresupuesto =:idpresupuesto ", Double.class)
                .setParameter("idpresupuesto", idpresupuesto)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst.get(0);
    }
}
