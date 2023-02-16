package br.com.lhos.wsassemblyvotemanager.repository;

import br.com.lhos.wsassemblyvotemanager.domain.Voto;
import br.com.lhos.wsassemblyvotemanager.enumeration.VotoSituacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VotoRepositoy extends JpaRepository<Voto, UUID> {
    boolean existsByAssociado_AssociadoId(UUID id);

    long countByPauta_PautaIdAndSituacaoLike(UUID id, VotoSituacaoEnum s);
}
