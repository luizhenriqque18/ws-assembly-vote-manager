package br.com.lhos.wsassemblyvotemanager.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private UUID votoId;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Column(nullable = false)
    private boolean situacao;
}
