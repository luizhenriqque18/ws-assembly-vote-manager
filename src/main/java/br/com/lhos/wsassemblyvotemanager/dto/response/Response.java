package br.com.lhos.wsassemblyvotemanager.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Classe que implementa uma resposta genérica para os end-points API.
 *
 * @author Luiz Souza
 * @since 14/02/2023
 *
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;
    private Object errors;

    /**
     * Método que formata uma mensagem de erro para a resposta HTTP.
     *
     * @author Luiz Souza
     * @since 14/02/2023
     *
     * @param msgError
     */
    public void addErrorMsgToResponse(String msgError) {
        ResponseError error = new ResponseError()
                .setDetails(msgError)
                .setTimestamp(LocalDateTime.now());
        setErrors(error);
    }
}