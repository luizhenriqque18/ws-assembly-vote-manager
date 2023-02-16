package br.com.lhos.wsassemblyvotemanager.dto;

import br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoDTO {

    private UUID sessaoVotacaoId;

    @NotBlank
    private String titulo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss")
    private LocalTime duracao = LocalTime.parse("00:01:00");

    @Pattern(regexp="^(PENDING|OPEN|CLOSED|CANCELED)$",
            message="Para o status só são aceitos os valores PENDING, OPEN, CLOSED ou CANCELED.")
    private String status;

    private LocalDateTime inicioSessao;

    private LocalDateTime encerraSessao;


    /**
     * Método para converter um @{@link SessaoVotacaoDTO} para uma entidade de @{@link SessaoVotacao}.
     *
     * @author Luiz Souza
     * @since 14/02/2023
     *
     * @return a <code>SessaoVotacao</code> object
     */
    public SessaoVotacao convertDTOToEntity() {
        return new ModelMapper().map(this, SessaoVotacao.class);
    }
}
