package br.com.fiap.monitoramento_ambiental_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex) {
        return ex.getMessage();
    }

    // Outros handlers...
}
