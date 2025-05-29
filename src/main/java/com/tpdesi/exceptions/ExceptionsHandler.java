package com.tpdesi.exceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    // 400 
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String errores = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .collect(Collectors.joining("; "));

        ErrorResponse body = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Solicitud inválida",
            errores,
            request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // 404
    @ExceptionHandler(RecursoNoEncontradoException.class)
    protected ResponseEntity<ErrorResponse> manejarRecursoNoEncontrado(
            RecursoNoEncontradoException ex,
            WebRequest request) {

        ErrorResponse body = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Recurso no encontrado",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // 400 
    @ExceptionHandler(NegocioException.class)
    protected ResponseEntity<ErrorResponse> manejarExcepcionNegocio(
            NegocioException ex,
            WebRequest request) {

        ErrorResponse body = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Error de negocio",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // 500 todos los errores internos
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> manejarTodasLasExcepciones(
            Exception ex,
            WebRequest request) {

        ErrorResponse body = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error interno del servidor",
            "Ocurrio un error inesperado.",
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
