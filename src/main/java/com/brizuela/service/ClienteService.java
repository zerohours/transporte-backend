package com.brizuela.service;

import com.brizuela.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Optional<Cliente> clienteById(Long id);
    Cliente create(Cliente cliente);
    Cliente update(Cliente cliente);
    List<Cliente> reader();
    void delete(Cliente cliente);
}
