package com.solec.api.ejb;

import com.solec.api.entity.Tipocantidad;
import com.solec.api.entity.Tipogasto;
import java.util.List;

/**
 *
 * @author elfo_
 */
public interface CatalogoBeanLocal {

    List<Tipocantidad> ListTipoCantidad();

    List<Tipogasto> ListYipoGasto();

}
