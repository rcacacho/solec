package com.solec.api.ejb;

import com.solec.api.entity.Gasto;
import java.util.List;

/**
 *
 * @author elfo_
 */
public interface GastoBeanLocal {

    List<Gasto> ListGastos();

    Gasto saveGasto(Gasto gasto);

    Gasto updateGasto(Gasto gasto);

    List<Gasto> ListGastoByNombre(String nombre);

    Gasto findGasto(Integer idgasto);

}
