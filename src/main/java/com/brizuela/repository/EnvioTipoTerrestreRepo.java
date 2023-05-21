package com.brizuela.repository;

import com.brizuela.model.EnvioTipoTerrestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioTipoTerrestreRepo extends JpaRepository<EnvioTipoTerrestre, Long> {
}
