package com.brizuela.controller;

import java.util.List;

import com.brizuela.exception.ClienteNotFoundException;
import com.brizuela.model.Cliente;
import com.brizuela.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/list")
    public List<Cliente> getAll() {
        return clienteService.reader();
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
        Cliente saved = clienteService.create(cliente);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(
            @PathVariable("id") Long id,
            @Valid @RequestBody Cliente cliente) {

        return clienteService.clienteById(id)
                .map(clienteToUpdate -> {
                    clienteToUpdate.setNombre(cliente.getNombre());
                    Cliente clienteUpdate = clienteService.update(clienteToUpdate);
                    return new ResponseEntity<>(clienteUpdate, HttpStatus.OK);
                })
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(
            @PathVariable("id") Long id) {
        return clienteService.clienteById(id)
                .map(clienteToDelete -> {
                    clienteService.delete(clienteToDelete);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                })
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }
}
