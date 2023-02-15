package br.com.lhos.wsassemblyvotemanager.service;

import br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao;
import br.com.lhos.wsassemblyvotemanager.exception.SessaoVotacaoNaoExisteEx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SessaoVotacaoService {
    SessaoVotacao save(SessaoVotacao pauta);
    SessaoVotacao findById(UUID id) throws SessaoVotacaoNaoExisteEx;
    Page<SessaoVotacao> findAll(Pageable pageable);
}
