package com.remedios.amber.curso.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.List;

@RestControllerAdvice
public class TratadorErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratador404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratador400(MethodArgumentNotValidException e){
        List<FieldError> error = e.getFieldErrors();

        return ResponseEntity.badRequest().body(error.stream().map(DadosErro::new).toList());
    }

    public record DadosErro(String campo, String mensagem){
        public DadosErro(FieldError erro ){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}