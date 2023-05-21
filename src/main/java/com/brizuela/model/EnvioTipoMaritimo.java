package com.brizuela.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "envio_maritimo")
@EqualsAndHashCode(callSuper = true)
public class EnvioTipoMaritimo extends Envio {

    @Column(name = "puerto_entrega")
    @NotNull(message = "Puerto de entrega es requeridoß")
    private String puertoEntrega;

    @Column(name = "numero_flota")
    @Pattern(regexp = "[A-Za-z]{3}[0-9]{4}[A-Za-z]{1}",message = "El numero de flota debe tener el formato ###$$$$#")
    private String numeroFlota;
}


