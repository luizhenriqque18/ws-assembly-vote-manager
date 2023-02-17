package br.com.lhos.wsassemblyvotemanager.domain;

import br.com.lhos.wsassemblyvotemanager.enumeration.SessaoVotacaoStatusEnum;
import br.com.lhos.wsassemblyvotemanager.enumeration.VotoSituacaoEnum;
import br.com.lhos.wsassemblyvotemanager.repository.PautaRepository;
import br.com.lhos.wsassemblyvotemanager.repository.SessaoVotacaoRepository;
import br.com.lhos.wsassemblyvotemanager.repository.VotoRepositoy;
import br.com.lhos.wsassemblyvotemanager.service.PautaService;
import br.com.lhos.wsassemblyvotemanager.service.SessaoVotacaoService;
import br.com.lhos.wsassemblyvotemanager.service.VotoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.PostLoad;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PautaListener {

    private static PautaService pautaService;
    private static SessaoVotacaoService sessaoVotacaoService;
    private static VotoService votoService;
    @Autowired
    public PautaListener(PautaService pautaService, SessaoVotacaoService sessaoVotacaoService, VotoService votoService) {
        this.pautaService = pautaService;
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.votoService = votoService;
    }

    @PostLoad
    public void afterLoad(Pauta pauta) {
        SessaoVotacao sessaoVotacao = pauta.getSessaoVotacao();
        if(sessaoVotacao.getEncerraSessao() != null){
            if(LocalDateTime.now().isAfter(sessaoVotacao.getEncerraSessao())){
                pauta.setCountAprovados(votoService.countVotosLike(pauta.getPautaId(), VotoSituacaoEnum.SIM));
                pauta.setCountReprovados(votoService.countVotosLike(pauta.getPautaId(), VotoSituacaoEnum.NAO));
                pautaService.save(pauta);

                sessaoVotacao.setStatus(SessaoVotacaoStatusEnum.CLOSED);

                sessaoVotacaoService.save(sessaoVotacao);
            }
        }
    }
}
