package com.solec.api.ejb;

import com.solec.api.entity.Presupuesto;
import java.util.List;

/**
 *
 * @author elfo_
 */
public interface PresupuestoBeanLocal {

    List<Presupuesto> ListPresupuestos();

    Presupuesto savePresupuesto(Presupuesto presupuesto);

    Presupuesto updatePresupuesto(Presupuesto presupuesto);

    List<Presupuesto> ListPresupuestoByNombre(String nombre);

    Presupuesto findPresupuesto(Integer idpresupuesto);

}