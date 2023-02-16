package br.com.lhos.wsassemblyvotemanager.dto;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID  pautaId;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private UUID sessaoVotacaoId;

    /**
     * Método para converter um @{@link PautaDTO} para uma entidade de @{@link Pauta}.
     *
     * @author Luiz Souza
     * @since 15/02/2023
     *
     * @return a <code>SessaoVotacao</code> object
     */
    public Pauta convertDTOToEntity() {
        return new ModelMapper().map(this, Pauta.class);
    }
}
