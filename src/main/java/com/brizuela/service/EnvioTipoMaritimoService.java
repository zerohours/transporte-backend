package com.brizuela.service;

import com.brizuela.model.EnvioTipoMaritimo;

import java.util.List;
import java.util.Optional;

public interface EnvioTipoMaritimoService {
    Optional<EnvioTipoMaritimo> maritimoById(Long id);
    EnvioTipoMaritimo create(EnvioTipoMaritimo maritimo);
    EnvioTipoMaritimo update(EnvioTipoMaritimo maritimo);
    List<EnvioTipoMaritimo> reader();
    void delete(EnvioTipoMaritimo maritimo);
}
