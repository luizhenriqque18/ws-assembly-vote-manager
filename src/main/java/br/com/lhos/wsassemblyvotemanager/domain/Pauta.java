package br.com.lhos.wsassemblyvotemanager.domain;

import br.com.lhos.wsassemblyvotemanager.dto.PautaDTO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PAUTA")
@EntityListeners(PautaListener.class)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pauta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID pautaId;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column
    private long countAprovados;

    @Column
    private long countReprovados;

    @Column
    private long resultado;

    @OneToOne
    @JoinColumn(name = "sessao_votacao_id", unique = true)
    private SessaoVotacao sessaoVotacao;

    /**
     * Método para converter uma entidade @{@link Pauta} para uma @{@link PautaDTO}.
     *
     * @author Luiz Souza
     * @since 14/02/2023
     *
     * @return a <code>PautaDTO</code> object
     */
    public PautaDTO convertEntityToDTO() {
        return new ModelMapper().map(this, PautaDTO.class);
    }
}
