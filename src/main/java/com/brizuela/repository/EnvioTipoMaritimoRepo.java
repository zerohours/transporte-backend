package com.brizuela.repository;

import com.brizuela.model.EnvioTipoMaritimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioTipoMaritimoRepo extends JpaRepository<EnvioTipoMaritimo, Long> {
}
