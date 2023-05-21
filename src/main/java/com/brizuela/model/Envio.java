package com.brizuela.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "envio")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_producto", length = 50, nullable = false)
    @NotNull(message = "Tipo de producto no puede ser nulo")
    private String tipoProducto;

    @Column(name = "cantidad_producto", nullable = false)
    @NotNull(message = "cantidad de producto no puede ser nulo")
    private Integer cantidadProducto;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_entrega")
    private Date fechaEntrega;

    @Column(name = "precio_envio", nullable = false)
    @NotNull(message = "Precio debe ser positivo")
    private BigDecimal precioEnvio;

    @Column(name = "numero_guia", length = 10, nullable = false)
    @Size(min = 10, max = 10, message = "la longitud del numero de guia debe ser de 10")
    @Pattern(regexp = "[A-Za-z0-9]{10}",message = "El numero de flota debe tener el formato ###$$$$#")
    private String numeroGuia;

    @Column(name = "precio_original", nullable = false)
    @NotNull(message = "Precio Original debe ser positivo o 0")
    private BigDecimal precioOriginal;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @NotNull(message = "Debe de seleccionar a un cliente")
    private Cliente cliente;
}
