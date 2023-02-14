package br.com.lhos.wsassemblyvotemanager.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;


@Entity
@Table(name = "TB_SESSAO_VOTACAO")
@Getter
@Setter
@NoArgsConstructor
public class SessaoVotacao implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sessaoVotacaoId;

    @Column
    private String titulo;

    @Column
    private LocalTime duracao;

    @Column
    private String statusSessao;

    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

}
