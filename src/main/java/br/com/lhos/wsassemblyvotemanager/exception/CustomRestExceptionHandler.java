package br.com.lhos.wsassemblyvotemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * Método que lida com uma @{@link SessaoVotacaoNaoExisteEx} e devolve um erro com
     * status code = 404.
     *
     * @author Luiz Souza
     * @since 15/02/2023
     *
     * @param exception
     * @return ResponseEntity<Response<T>>
     */
    @ExceptionHandler(value = { SessaoVotacaoNaoExisteEx.class })
    protected ResponseEntity<Response<T>> sessaoVotacaoNãoExisteEx(SessaoVotacaoNaoExisteEx exception) {

        Response<T> response = new Response<>();
        response.addErrorMsgToResponse(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Método que lida com uma @{@link PautaNaoExisteEx} e devolve um erro com
     * status code = 404.
     *
     * @author Luiz Souza
     * @since 15/02/2023
     *
     * @param exception
     * @return ResponseEntity<Response<T>>
     */
    @ExceptionHandler(value = { PautaNaoExisteEx.class })
    protected ResponseEntity<Response<T>> pautaNãoExisteEx(PautaNaoExisteEx exception) {

        Response<T> response = new Response<>();
        response.addErrorMsgToResponse(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Método que lida com uma @{@link SessaoVotacaoEmProgressoEx} e devolve um erro com
     * status code = 404.
     *
     * @author Luiz Souza
     * @since 15/02/2023
     *
     * @param exception
     * @return ResponseEntity<Response<T>>
     */
    @ExceptionHandler(value = { SessaoVotacaoEmProgressoEx.class, AssociadoNaoExisteEx.class})
    protected ResponseEntity<Response<T>> pautaNãoExisteEx(Exception exception) {

        Response<T> response = new Response<>();
        response.addErrorMsgToResponse(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
