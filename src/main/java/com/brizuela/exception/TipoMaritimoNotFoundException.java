package com.brizuela.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TipoMaritimoNotFoundException extends RuntimeException {

    public TipoMaritimoNotFoundException(Long id) {
        super("No se puede encontrar envio con id: `" + id + "`");
    }
}
