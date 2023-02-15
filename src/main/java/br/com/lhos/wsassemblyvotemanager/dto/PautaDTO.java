package br.com.lhos.wsassemblyvotemanager.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;
}
