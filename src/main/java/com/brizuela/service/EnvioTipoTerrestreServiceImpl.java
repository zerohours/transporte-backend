package com.brizuela.service;

import com.brizuela.model.EnvioTipoTerrestre;
import com.brizuela.repository.EnvioTipoTerrestreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnvioTipoTerrestreServiceImpl implements EnvioTipoTerrestreService {

    private final EnvioTipoTerrestreRepo terrestreRepo;

    @Autowired
    public EnvioTipoTerrestreServiceImpl(EnvioTipoTerrestreRepo terrestreRepo) {
        this.terrestreRepo = terrestreRepo;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<EnvioTipoTerrestre> terrestreById(Long id) {
        return terrestreRepo.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public EnvioTipoTerrestre create(EnvioTipoTerrestre terrestre) {
        return terrestreRepo.save(terrestre);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public EnvioTipoTerrestre update(EnvioTipoTerrestre terrestre) {
        return terrestreRepo.save(terrestre);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<EnvioTipoTerrestre> reader() {
        return terrestreRepo.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(EnvioTipoTerrestre terrestre) {
        terrestreRepo.delete(terrestre);
    }
}
