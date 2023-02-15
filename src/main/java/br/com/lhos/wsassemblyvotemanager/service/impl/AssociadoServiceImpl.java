package br.com.lhos.wsassemblyvotemanager.service.impl;

import br.com.lhos.wsassemblyvotemanager.domain.Associado;
import br.com.lhos.wsassemblyvotemanager.exception.AssociadoNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.repository.AssociadoRepository;
import br.com.lhos.wsassemblyvotemanager.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    AssociadoRepository associadoRepository;

    @Autowired
    public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    public Associado save(Associado associado) {
        return associadoRepository.save(associado);
    }

    @Override
    public Associado findById(UUID id) throws AssociadoNaoExisteEx {
        return associadoRepository.findById(id).orElseThrow( () -> new AssociadoNaoExisteEx("Associado " + id+ " não existe."));
    }

    @Override
    public Page<Associado> findAll(Pageable pageable) {
        return associadoRepository.findAll(pageable);
    }
}
