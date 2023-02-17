package br.com.lhos.wsassemblyvotemanager.service.impl;

import br.com.lhos.wsassemblyvotemanager.domain.Voto;
import br.com.lhos.wsassemblyvotemanager.enumeration.VotoSituacaoEnum;
import br.com.lhos.wsassemblyvotemanager.exception.SessaoVotacaoNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.exception.VotoJaRegistradoEx;
import br.com.lhos.wsassemblyvotemanager.repository.VotoRepositoy;
import br.com.lhos.wsassemblyvotemanager.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VotoServiceImpl implements VotoService {

    VotoRepositoy votoRepositoy;

    @Autowired
    public VotoServiceImpl(VotoRepositoy votoRepositoy) {
        this.votoRepositoy = votoRepositoy;
    }

    @Override
    public Voto save(Voto voto) {
        return votoRepositoy.save(voto);
    }

    @Override
    public boolean existAssociado(UUID id) throws VotoJaRegistradoEx {
        boolean result = votoRepositoy.existsByAssociado_AssociadoId(id);
        if(result){
            throw new VotoJaRegistradoEx("Associado " + id + " já registrou seu voto ");
        }
        return false;
    }

    @Override
    public long countVotosLike(UUID id, VotoSituacaoEnum s) {
        return votoRepositoy.countByPauta_PautaIdAndSituacaoLike(id, s);
    }
}
