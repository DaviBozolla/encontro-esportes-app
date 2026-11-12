package com.davi.encontro_esportes.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorErros {

    // 1. Erros de validação (@Valid) → 400 com { campo: mensagem }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<String, String>();

        for (var erro : ex.getBindingResult().getFieldErrors()) {
            erros.put(erro.getField(), erro.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> tratarRequest(ResponseStatusException ex) {
        Map<String, String> erro = Map.of("erro", ex.getReason());
        
        return ResponseEntity.status(ex.getStatusCode()).body(erro);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> tratarInput(MethodArgumentTypeMismatchException ex) {
        Map<String, String> erro = Map.of("erro", "Valor inválido para o parâmetro '" + ex.getName() + "'");

        return ResponseEntity.badRequest().body(erro);
    }
}
