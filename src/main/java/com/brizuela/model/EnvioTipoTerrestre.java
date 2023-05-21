package com.brizuela.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "envio_terrestre")
@EqualsAndHashCode(callSuper = true)
public class EnvioTipoTerrestre extends Envio {

    @NotNull(message = "Bodega de entrega es requerido")
    @Column(name = "bodega_entrega", nullable = false)
    private String bodegaEntrega;

    @NotNull(message = "Numero de placa es requerido")
    @Column(name = "numero_placa", nullable = false)
    @Pattern(regexp = "[A-Za-z]{3}[0-9]{3}",message = "El numero de flota debe tener el formato ###$$$")
    private String numeroPlaca;

}
