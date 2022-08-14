package com.solec.api.ejb;

import com.solec.api.entity.Tipocantidad;
import com.solec.api.entity.Tipogasto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author elfo_
 */
@Local
public interface CatalogoBeanLocal {

    List<Tipocantidad> ListTipoCantidad();

    List<Tipogasto> ListTipoGasto();

}
