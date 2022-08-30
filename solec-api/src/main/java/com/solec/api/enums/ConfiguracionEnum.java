package com.solec.api.enums;

/**
 *
 * @author rcacacho
 */
public enum ConfiguracionEnum {
    
    RUTA_ARCHIVO("RUTA_ARCHIVO");

    private String parametro;

    private ConfiguracionEnum(String parametro) {
        this.parametro = parametro;
    }

    public String getParametro() {
        return parametro;
    }
    
}
