package br.com.lhos.wsassemblyvotemanager.service;

import br.com.lhos.wsassemblyvotemanager.domain.Voto;
import br.com.lhos.wsassemblyvotemanager.enumeration.VotoSituacaoEnum;
import br.com.lhos.wsassemblyvotemanager.exception.VotoJaRegistradoEx;
import java.util.UUID;

public interface VotoService {
    Voto save(Voto pauta);
    boolean existAssociado(UUID id) throws VotoJaRegistradoEx;

    long countVotosLike(UUID id, VotoSituacaoEnum s);
}
