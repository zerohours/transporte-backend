package com.brizuela.controller;

import com.brizuela.exception.TipoMaritimoNotFoundException;
import com.brizuela.model.EnvioTipoMaritimo;
import com.brizuela.service.EnvioTipoMaritimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/maritimo")
public class EnvioTipoMaritimoController {

    private final EnvioTipoMaritimoService tipoMaritimoService;

    @Autowired
    public EnvioTipoMaritimoController(EnvioTipoMaritimoService tipoMaritimoService) {
        this.tipoMaritimoService = tipoMaritimoService;
    }

    @GetMapping("/list")
    public List<EnvioTipoMaritimo> getAll() {
        return tipoMaritimoService.reader();
    }

    @PostMapping
    public ResponseEntity<EnvioTipoMaritimo> createTipoTerrestre(
            @Valid @RequestBody EnvioTipoMaritimo maritimo) {
        EnvioTipoMaritimo saved = tipoMaritimoService.create(maritimo);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioTipoMaritimo> updateTipoTerrestre(
            @PathVariable("id") Long id,
            @Valid @RequestBody EnvioTipoMaritimo maritimo) {

        return tipoMaritimoService.maritimoById(id)
                .map(maritimoToUpdate -> {

                    maritimoToUpdate.setPrecioEnvio(maritimo.getPrecioEnvio());
                    maritimoToUpdate.setCantidadProducto(maritimo.getCantidadProducto());
                    maritimoToUpdate.setCliente(maritimo.getCliente());
                    maritimoToUpdate.setPuertoEntrega(maritimo.getPuertoEntrega());
                    maritimoToUpdate.setFechaRegistro(maritimo.getFechaRegistro());
                    maritimoToUpdate.setFechaEntrega(maritimo.getFechaEntrega());
                    maritimoToUpdate.setNumeroFlota(maritimo.getNumeroFlota());
                    maritimoToUpdate.setPrecioOriginal(maritimo.getPrecioOriginal());
                    maritimoToUpdate.setNumeroGuia(maritimo.getNumeroGuia());

                    EnvioTipoMaritimo terrestreUpdate = tipoMaritimoService.update(maritimoToUpdate);
                    return new ResponseEntity<>(terrestreUpdate, HttpStatus.OK);
                })
                .orElseThrow(() -> new TipoMaritimoNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTipoTerrestre(
            @PathVariable("id") Long id) {
        return tipoMaritimoService.maritimoById(id)
                .map(maritimoToDelete -> {
                    tipoMaritimoService.delete(maritimoToDelete);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                })
                .orElseThrow(() -> new TipoMaritimoNotFoundException(id));
    }
}
