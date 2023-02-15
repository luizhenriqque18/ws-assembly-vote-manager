package br.com.lhos.wsassemblyvotemanager.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PAUTA")
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
}
