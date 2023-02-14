package br.com.lhos.wsassemblyvotemanager.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Classe que implementa um objeto de erro de resposta genérico para os end-points da API.
 *
 * @author Luiz Souza
 * @since 14/02/2023
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ResponseError {

    @NotNull(message="Timestamp não pode ser null")
    private LocalDateTime timestamp;

    @NotNull(message="Details não pode ser null")
    private String details;

}