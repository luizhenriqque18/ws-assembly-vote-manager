package br.com.lhos.wsassemblyvotemanager.dto;

import br.com.lhos.wsassemblyvotemanager.domain.Voto;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {
    private UUID votoId;
    @NotNull
    private UUID pautaId;

    @NotNull
    private UUID associadoId;

    @Pattern(regexp="^(SIM|NAO)$",
            message="Para situacao só são aceitos os valores SIM ou NAO.")
    private String situacao;


    /**
     * Método para converter um @{@link VotoDTO} para uma entidade de @{@link Voto}.
     *
     * @author Luiz Souza
     * @since 14/02/2023
     *
     * @return a <code>Voto</code> object
     */
    public Voto convertDTOToEntity() {
        return new ModelMapper().map(this, Voto.class);
    }
}
