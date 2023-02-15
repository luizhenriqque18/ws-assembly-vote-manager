package br.com.lhos.wsassemblyvotemanager.service.impl;

import br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao;
import br.com.lhos.wsassemblyvotemanager.exception.SessaoVotacaoNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.repository.SessaoVotacaoRepository;
import br.com.lhos.wsassemblyvotemanager.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessaoVotacaoImplService implements SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    public SessaoVotacaoImplService(SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    @Override
    public SessaoVotacao save(SessaoVotacao sessaoVotacao) {
        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    @Override
    public SessaoVotacao findById(UUID id) throws SessaoVotacaoNaoExisteEx {
        return sessaoVotacaoRepository.findById(id).orElseThrow(() -> new SessaoVotacaoNaoExisteEx("SessaoVotacao id " + id + " não existe."));
    }

    @Override
    public Page<SessaoVotacao> findAll(Pageable pageable) {
        return sessaoVotacaoRepository.findAll(pageable);
    }
}
