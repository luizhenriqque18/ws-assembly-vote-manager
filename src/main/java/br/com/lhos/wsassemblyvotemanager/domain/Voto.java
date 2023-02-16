package br.com.lhos.wsassemblyvotemanager.domain;

import br.com.lhos.wsassemblyvotemanager.dto.SessaoVotacaoDTO;
import br.com.lhos.wsassemblyvotemanager.dto.VotoDTO;
import br.com.lhos.wsassemblyvotemanager.enumeration.VotoSituacaoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_VOTO")
@Getter
@Setter
@NoArgsConstructor
public class Voto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID votoId;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Enumerated(EnumType.STRING)
    private VotoSituacaoEnum situacao;

    /**
     * Método para converter um @{@link VotoDTO} para uma entidade de @{@link Voto}.
     *
     * @author Luiz Souza
     * @since 14/02/2023
     *
     * @return a <code>Voto</code> object
     */
    public VotoDTO convertEntityToDTO() {
        return new ModelMapper().map(this, VotoDTO.class);
    }
}
