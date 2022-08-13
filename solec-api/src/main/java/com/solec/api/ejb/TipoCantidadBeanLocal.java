package com.solec.api.ejb;

import com.solec.api.entity.Tipocantidad;
import java.util.List;

/**
 *
 * @author elfo_
 */
public interface TipoCantidadBeanLocal {

    List<Tipocantidad> ListTipoCantidad();

    Tipocantidad saveTipoCantidad(Tipocantidad tipoCantidad);

    Tipocantidad updateTipoCantidad(Tipocantidad tipoCantidad);

    Tipocantidad deleteTipoCantidad(Integer idtipogasto, String usuario);

}
