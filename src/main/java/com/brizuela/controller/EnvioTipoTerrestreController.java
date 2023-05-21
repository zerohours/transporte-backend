package com.brizuela.controller;

import com.brizuela.exception.TipoTerrestreNotFoundException;
import com.brizuela.model.EnvioTipoTerrestre;
import com.brizuela.service.EnvioTipoTerrestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/terrestre")
public class EnvioTipoTerrestreController {

    private final EnvioTipoTerrestreService tipoTerrestreService;

    @Autowired
    public EnvioTipoTerrestreController(EnvioTipoTerrestreService tipoTerrestreService) {
        this.tipoTerrestreService = tipoTerrestreService;
    }

    @GetMapping("/list")
    public List<EnvioTipoTerrestre> getAll() {
        return tipoTerrestreService.reader();
    }

    @PostMapping
    public ResponseEntity<EnvioTipoTerrestre> createTipoTerrestre(
            @Valid @RequestBody EnvioTipoTerrestre terrestre) {
        EnvioTipoTerrestre saved = tipoTerrestreService.create(terrestre);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioTipoTerrestre> updateTipoTerrestre(
            @PathVariable("id") Long id,
            @Valid @RequestBody EnvioTipoTerrestre terrestre) {

        return tipoTerrestreService.terrestreById(id)
                .map(terrestreToUpdate -> {

                    terrestreToUpdate.setPrecioEnvio(terrestre.getPrecioEnvio());
                    terrestreToUpdate.setCantidadProducto(terrestre.getCantidadProducto());
                    terrestreToUpdate.setCliente(terrestre.getCliente());
                    terrestreToUpdate.setBodegaEntrega(terrestre.getBodegaEntrega());
                    terrestreToUpdate.setFechaRegistro(terrestre.getFechaRegistro());
                    terrestreToUpdate.setFechaEntrega(terrestre.getFechaEntrega());
                    terrestreToUpdate.setNumeroPlaca(terrestre.getNumeroPlaca());
                    terrestreToUpdate.setPrecioOriginal(terrestre.getPrecioOriginal());
                    terrestreToUpdate.setNumeroGuia(terrestre.getNumeroGuia());

                    EnvioTipoTerrestre terrestreUpdate = tipoTerrestreService.update(terrestreToUpdate);
                    return new ResponseEntity<>(terrestreUpdate, HttpStatus.OK);
                })
                .orElseThrow(() -> new TipoTerrestreNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTipoTerrestre(
            @PathVariable("id") Long id) {
        return tipoTerrestreService.terrestreById(id)
                .map(terrestreToDelete -> {
                    tipoTerrestreService.delete(terrestreToDelete);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                })
                .orElseThrow(() -> new TipoTerrestreNotFoundException(id));
    }
}
