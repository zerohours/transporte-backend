package com.brizuela.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EnvioTipoTerrestre extends Envio {

    @NotNull(message = "Bodega de entrega es requerido")
    @Column(name = "bodega_entrega", nullable = false)
    private String bodegaEntrega;

    @NotNull(message = "Numero de placa es requerido")
    @Column(name = "numero_placa", nullable = false)
    @Pattern(regexp = "[A-Za-z]{3}[0-9]{3}",message = "El numero de flota debe tener el formato ###$$$")
    private String numeroPlaca;

}
