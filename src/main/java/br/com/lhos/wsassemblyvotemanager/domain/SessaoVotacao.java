package br.com.lhos.wsassemblyvotemanager.domain;

import br.com.lhos.wsassemblyvotemanager.dto.SessaoVotacaoDTO;
import br.com.lhos.wsassemblyvotemanager.enumeration.SessaoVotacaoStatusEnum;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;


@Entity
@Table(name = "TB_SESSAO_VOTACAO")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID sessaoVotacaoId;

    @Column
    private String titulo;

    @Column
    private LocalTime duracao;

    @Column
    private LocalDateTime inicioSessao;

    @Column
    private LocalDateTime encerraSessao;

    @Enumerated(EnumType.STRING)
    private SessaoVotacaoStatusEnum status;

    /**
     * Método para converter um @{@link SessaoVotacaoDTO} para uma entidade de @{@link SessaoVotacao}.
     *
     * @author Luiz Souza
     * @since 14/02/2023
     *
     * @return a <code>SessaoVotacao</code> object
     */
    public SessaoVotacaoDTO convertEntityToDTO() {
        return new ModelMapper().map(this, SessaoVotacaoDTO.class);
    }

}
