package com.brizuela.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EnvioTipoMaritimo {

    @Column(name = "puerto_entrega")
    @NotNull(message = "Puerto de entrega es requeridoß")
    private String puertoEntrega;

    @Column(name = "numero_flota")
    @Pattern(regexp = "[A-Za-z]{3}[0-9]{4}[A-Za-z]{1}",message = "El numero de flota debe tener el formato ###$$$$#")
    private String numeroFlota;
}
