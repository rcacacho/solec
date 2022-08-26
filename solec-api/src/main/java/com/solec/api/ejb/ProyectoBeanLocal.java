package com.solec.api.ejb;


import com.solec.api.entity.Detalleproyecto;
import com.solec.api.entity.Proyectos;
import java.util.List;

/**
 *
 * @author elfo_
 */
public interface ProyectoBeanLocal {

    List<Proyectos> ListProyectos();

    Proyectos saveProyectos(Proyectos proyecto);

    Proyectos updateProyecto(Proyectos proyecto);

    List<Proyectos> ListProyectoByNombre(String nombre);

    Proyectos findProyecto(Integer idpresupuesto);
    
    Detalleproyecto saveDetalleProyecto(Detalleproyecto detallePresupuesto);
    
    List<Detalleproyecto> ListDetalleProyectoByIdPresupuesto(Integer idpresupuesto);
    
    Double finDetalleProyectoSumByIdProyecto(Integer idpresupuesto);

}
