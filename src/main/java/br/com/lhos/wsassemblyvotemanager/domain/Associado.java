package br.com.lhos.wsassemblyvotemanager.domain;

import br.com.lhos.wsassemblyvotemanager.dto.AssociadoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ASSOCIADO")
@Getter @Setter @NoArgsConstructor
public class Associado implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID associadoId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    /**
     * Método para converter um @{@link Associado} para uma entidade de @{@link AssociadoDTO}.
     *
     * @author Luiz Souza
     * @since 15/02/2023
     *
     * @return a <code>AssociadoDTO</code> object
     */
    public AssociadoDTO convertEntityToDTO() {
        return new ModelMapper().map(this, AssociadoDTO.class);
    }
}
