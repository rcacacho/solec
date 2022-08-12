package com.solec.bussines.imp;

import com.solec.api.ejb.CatalogoBeanLocal;
import com.solec.api.entity.Tipocantidad;
import com.solec.api.entity.Tipogasto;
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
public class CatalogoBean implements CatalogoBeanLocal {

    private static final Logger log = Logger.getLogger(CatalogoBean.class);

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
    public List<Tipogasto> ListYipoGasto() {
        List<Tipogasto> lst = em.createQuery("SELECT qj FROM Tipogasto qj where qj.activo = true ", Tipogasto.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

}
