package br.com.lhos.wsassemblyvotemanager.service.impl;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import br.com.lhos.wsassemblyvotemanager.exception.PautaNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.repository.PautaRepository;
import br.com.lhos.wsassemblyvotemanager.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PautaServiceImpl implements PautaService {
    PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(PautaRepository repository) {
        this.pautaRepository = repository;
    }

    @Override
    public Pauta save(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta findById(UUID id) throws PautaNaoExisteEx {
        return pautaRepository.findById(id).orElseThrow(() -> new PautaNaoExisteEx("Pauta id " + id + " não existe."));
    }

    @Override
    public Page<Pauta> findAll(Pageable pageable) {
        return pautaRepository.findAll(pageable);
    }
}
