package com.solec.api.ejb;

import com.solec.api.entity.Tipogasto;
import java.util.List;

/**
 *
 * @author elfo_
 */
public interface TipoGastoBeanLocal {

    List<Tipogasto> ListTipoGasto();

    Tipogasto saveTipoGasto(Tipogasto tipoGasto);

    Tipogasto updateTipoGasto(Tipogasto tipoGasto);

    Tipogasto deleteTipoGasto(Integer idtipocantidad, String usuario);

}
