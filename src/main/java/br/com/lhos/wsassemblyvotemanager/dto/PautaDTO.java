package br.com.lhos.wsassemblyvotemanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Builder
public class PautaDTO {
    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;
}
