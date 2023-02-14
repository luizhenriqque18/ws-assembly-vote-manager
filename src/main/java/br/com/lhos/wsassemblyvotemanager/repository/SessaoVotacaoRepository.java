package br.com.lhos.wsassemblyvotemanager.repository;

import br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, UUID> {
}
