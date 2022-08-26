package com.solec.bussines.imp;

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
import com.solec.api.ejb.ProyectoBeanLocal;
import com.solec.api.entity.Detalleproyecto;
import com.solec.api.entity.Proyectos;

/**
 *
 * @author elfo_
 */
@Singleton
public class ProyectoBean implements ProyectoBeanLocal {

    private static final Logger log = Logger.getLogger(ProyectoBean.class);

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
    public List<Proyectos> ListProyectos() {
        List<Proyectos> lst = em.createQuery("SELECT qj FROM Proyectos qj where qj.activo = true ", Proyectos.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Proyectos saveProyectos(Proyectos proyecto) {
        try {
            proyecto.setActivo(true);
            proyecto.setFechacreacion(new Date());
            em.persist(proyecto);
            em.flush();
            return (proyecto);
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
    public Proyectos updateProyecto(Proyectos proyecto) {
        try {
            em.merge(proyecto);
            em.flush();
            return (proyecto);
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
    public List<Proyectos> ListProyectoByNombre(String nombre) {
        if (nombre == null) {
            return null;
        }

        List<Proyectos> lst = em.createQuery("SELECT col FROM Proyectos col WHERE col.nombre like :nombre ", Proyectos.class)
                .setParameter("nombre", '%' + nombre + '%')
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst;
    }

    @Override
    public Proyectos findProyecto(Integer idpresupuesto) {
        if (idpresupuesto == null) {
            return null;
        }

        List<Proyectos> lst = em.createQuery("SELECT col FROM Proyectos col WHERE col.idpresupuesto =:idpresupuesto ", Proyectos.class)
                .setParameter("idpresupuesto", idpresupuesto)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst.get(0);
    }

    @Override
    public Detalleproyecto saveDetalleProyecto(Detalleproyecto detallePresupuesto) {
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
    public List<Detalleproyecto> ListDetalleProyectoByIdPresupuesto(Integer idpresupuesto) {
        if (idpresupuesto == null) {
            return null;
        }

        List<Detalleproyecto> lst = em.createQuery("SELECT col FROM Detalleproyecto col WHERE col.idpresupuesto.idpresupuesto =:idpresupuesto and col.activo = true order by col.fechacreacion desc ", Detalleproyecto.class)
                .setParameter("idpresupuesto", idpresupuesto)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst;
    }

    @Override
    public Double finDetalleProyectoSumByIdProyecto(Integer idpresupuesto) {
        if (idpresupuesto == null) {
            return null;
        }

        List<Double> lst = em.createQuery("SELECT sum (col.total) FROM Detalleproyecto col WHERE col.idpresupuesto.idpresupuesto =:idpresupuesto ", Double.class)
                .setParameter("idpresupuesto", idpresupuesto)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst.get(0);
    }

    @Override
    public Detalleproyecto eliminarDetalleProyecto(Integer iddetallepresupuesto, String usuario, String motivo) {
        if (iddetallepresupuesto == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            Detalleproyecto toUpdate = em.find(Detalleproyecto.class, iddetallepresupuesto);

            toUpdate.setActivo(false);
            toUpdate.setFechaeliminacion(new Date());
            toUpdate.setUsuarioeliminacion(usuario);
            toUpdate.setMotivoeliminacion(motivo);
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
    public Detalleproyecto updateDetalleProyecto(Detalleproyecto detalle) {
        try {
            em.merge(detalle);
            em.flush();
            return (detalle);
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
}
