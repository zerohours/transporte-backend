package com.brizuela.service;

import com.brizuela.model.Cliente;
import com.brizuela.repository.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepo clienteRepo;

    @Autowired
    public ClienteServiceImpl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Cliente> clienteById(Long id) {
        return clienteRepo.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Cliente create(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Cliente update(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Cliente> reader() {
        return clienteRepo.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Cliente cliente) {
        clienteRepo.delete(cliente);
    }
}
