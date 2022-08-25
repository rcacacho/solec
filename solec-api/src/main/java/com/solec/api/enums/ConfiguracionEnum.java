package com.solec.api.enums;

/**
 *
 * @author rcacacho
 */
public enum ConfiguracionEnum {
    
    CARPETA_ARCHIVOS("CARPETA_ARCHIVOS");

    private String parametro;

    private ConfiguracionEnum(String parametro) {
        this.parametro = parametro;
    }

    public String getParametro() {
        return parametro;
    }
    
}
