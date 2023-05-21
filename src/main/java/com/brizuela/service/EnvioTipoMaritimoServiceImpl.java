package com.brizuela.service;

import com.brizuela.model.EnvioTipoMaritimo;
import com.brizuela.repository.EnvioTipoMaritimoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnvioTipoMaritimoServiceImpl implements EnvioTipoMaritimoService {

    private final EnvioTipoMaritimoRepo maritimoRepo;

    @Autowired
    public EnvioTipoMaritimoServiceImpl(EnvioTipoMaritimoRepo maritimoRepo) {
        this.maritimoRepo = maritimoRepo;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<EnvioTipoMaritimo> maritimoById(Long id) {
        return maritimoRepo.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public EnvioTipoMaritimo create(EnvioTipoMaritimo maritimo) {
        return maritimoRepo.save(maritimo);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public EnvioTipoMaritimo update(EnvioTipoMaritimo maritimo) {
        return maritimoRepo.save(maritimo);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<EnvioTipoMaritimo> reader() {
        return maritimoRepo.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(EnvioTipoMaritimo maritimo) {
        maritimoRepo.delete(maritimo);
    }
}
