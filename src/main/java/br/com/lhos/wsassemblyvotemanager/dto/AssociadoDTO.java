package br.com.lhos.wsassemblyvotemanager.dto;

import br.com.lhos.wsassemblyvotemanager.domain.Associado;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDTO {
    private UUID associadoId;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;


    /**
     * Método para converter um @{@link AssociadoDTO} para uma entidade de @{@link Associado}.
     *
     * @author Luiz Souza
     * @since 15/02/2023
     *
     * @return a <code>Associado</code> object
     */
    public Associado convertDTOToEntity() {
        return new ModelMapper().map(this, Associado.class);
    }
}
