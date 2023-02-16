package br.com.lhos.wsassemblyvotemanager.service;

import br.com.lhos.wsassemblyvotemanager.domain.Associado;
import br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao;
import br.com.lhos.wsassemblyvotemanager.exception.AssociadoNaoExisteEx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AssociadoService {
    Associado save(Associado associado);
    Associado findById(UUID id) throws AssociadoNaoExisteEx;
    Page<Associado> findAll(Pageable pageable);
}
