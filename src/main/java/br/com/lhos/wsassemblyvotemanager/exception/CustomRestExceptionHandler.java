package br.com.lhos.wsassemblyvotemanager.exception;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

import br.com.lhos.wsassemblyvotemanager.dto.response.Response;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * Classe que implementa um manipulador de exceções e erros na API, utilizando {@ControllerAdvice}
 * e enviando a resposta adequada ao cliente.
 *
 * @author Luiz Souza
 * @since 14/02/2023
 *
 * @param <T>
 */
@ControllerAdvice
@RestController
public class CustomRestExceptionHandler<T> {

    /**
     * Método que trata com uma MethodArgumentNotValidException e devolve um erro com
     * código de estado = 400.
     *
     *
     * @author Luiz Souza
     * @since 14/02/2023
     *
     * @param exception
     * @return ResponseEntity<Response<T>>
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<T> handleValidationError(MethodArgumentNotValidException exception) {
        Response<T> response = new Response<>();
        Map<String, String> errors = new HashMap<>();
        if (exception.hasFieldErrors()) {
            for (FieldError error : exception.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
        }
        response.setErrors(errors);
        return response;
    }
}
