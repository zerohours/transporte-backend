package com.brizuela.service;

import com.brizuela.model.EnvioTipoTerrestre;

import java.util.List;
import java.util.Optional;

public interface EnvioTipoTerrestreService {
    Optional<EnvioTipoTerrestre> terrestreById(Long id);
    EnvioTipoTerrestre create(EnvioTipoTerrestre terrestre);
    EnvioTipoTerrestre update(EnvioTipoTerrestre terrestre);
    List<EnvioTipoTerrestre> reader();
    void delete(EnvioTipoTerrestre terrestre);
}
