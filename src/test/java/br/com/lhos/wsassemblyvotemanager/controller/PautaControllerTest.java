package br.com.lhos.wsassemblyvotemanager.controller;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao;
import br.com.lhos.wsassemblyvotemanager.dto.PautaDTO;
import br.com.lhos.wsassemblyvotemanager.exception.PautaNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.exception.SessaoVotacaoNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.service.PautaService;
import br.com.lhos.wsassemblyvotemanager.service.SessaoVotacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
class PautaControllerTest {

    static final String TITULO = "Assenbleia 0";
    static final String DESCRICAO = "Taxa de Juros veiculo";
    static final UUID PAUTA_ID = UUID.randomUUID();

    static final UUID SESSAO_VOTACAO_ID = UUID.randomUUID();
    static final String URL = "/pauta";


    HttpHeaders headers;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PautaService pautaService;

    @MockBean
    SessaoVotacaoService sessaoVotacaoService;

    @BeforeAll
    private void setUp() throws PautaNaoExisteEx, SessaoVotacaoNaoExisteEx {
        headers = new HttpHeaders();
        headers.set("X-api-key", "FX001-ZBSY6YSLP");

        SessaoVotacao sessaoVotacao = SessaoVotacao.builder().sessaoVotacaoId(SESSAO_VOTACAO_ID).build();

        Pauta pauta = Pauta.builder()
                .pautaId(PAUTA_ID)
                .titulo(TITULO)
                .descricao(DESCRICAO)
                .countAprovados(0)
                .countReprovados(0)
                .resultado(0)
                .sessaoVotacao(sessaoVotacao)
                .build();

        List<Pauta> pautas = new ArrayList<>();
        pautas.add(pauta);

        BDDMockito.given(sessaoVotacaoService.findById(Mockito.any(UUID.class))).willReturn(sessaoVotacao);
        BDDMockito.given(pautaService.save(Mockito.any(Pauta.class))).willReturn(pauta);
        BDDMockito.given(pautaService.findById(Mockito.any(UUID.class))).willReturn(pauta);
        BDDMockito.given(pautaService.findAll(Mockito.any(Pageable.class))).willReturn(new PageImpl<>(pautas));
    }

    /**
     * Metodo que testa salvar um objeto Pauta na API
	 *
     * @author Luiz Souza
	 * @since 13/02/2023
            *
            * @throws Exception
	 */
    @Test
    @Order(1)
    void testSave() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(TITULO, DESCRICAO, SESSAO_VOTACAO_ID))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pautaId").value(PAUTA_ID.toString()))
                .andExpect(jsonPath("$.titulo").value(TITULO))
                .andExpect(jsonPath("$.descricao").value(DESCRICAO))
                .andExpect(jsonPath("$.sessaoVotacaoId").value(SESSAO_VOTACAO_ID.toString()));
    }

    /**
     * Metodo que testa salvar um objeto Pauta invalido na API
     *
     * @author Luiz Souza
     * @since 13/02/2023
     *
     * @throws Exception
     */
    @Test
    @Order(2)
    void testSaveInvalidPauta() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(null, null, null))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.titulo").value("não deve estar em branco"))
                .andExpect(jsonPath("$.errors.descricao").value("não deve estar em branco"));
    }

    /**
     * Metodo que testa atualizar um objeto Pauta na API
     *
     * @author Luiz Souza
     * @since 13/02/2023
     *
     * @throws Exception
     */
    @Test
    @Order(3)
    void testUpdatePauta() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/{id}", PAUTA_ID).content(getJsonPayload(TITULO, DESCRICAO, SESSAO_VOTACAO_ID))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pautaId").value(PAUTA_ID.toString()))
                .andExpect(jsonPath("$.titulo").value(TITULO))
                .andExpect(jsonPath("$.descricao").value(DESCRICAO))
                .andExpect(jsonPath("$.sessaoVotacaoId").value(SESSAO_VOTACAO_ID.toString()));
    }

    /**
     * Metodo que testa encontrar um objeto Pauta na API por ID
     *
     * @author Luiz Souza
     * @since 13/02/2023
     *
     * @throws Exception
     */
    @Test
    @Order(4)
    void testFindByIdPauta() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/{id}", PAUTA_ID)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pautaId").value(PAUTA_ID.toString()))
                .andExpect(jsonPath("$.titulo").value(TITULO))
                .andExpect(jsonPath("$.descricao").value(DESCRICAO))
                .andExpect(jsonPath("$.countAprovados").value(0))
                .andExpect(jsonPath("$.countReprovados").value(0))
                .andExpect(jsonPath("$.resultado").value(0));
    }

    /**
     * Metodo que testa encontrar um objeto Pauta Invalido na API por ID
     *
     * @author Luiz Souza
     * @since 13/02/2023
     *
     * @throws Exception
     */
    @Test
    @Order(5)
    void testFindByIdInvalidPauta() throws Exception {
        PautaNaoExisteEx ex = new PautaNaoExisteEx("Pauta id " + PAUTA_ID + " não existe.");
        BDDMockito.given(pautaService.findById(Mockito.any(UUID.class))).willThrow(ex).willReturn(new Pauta());

        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/{id}", PAUTA_ID)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors.details").value("Pauta id " + PAUTA_ID + " não existe."));
    }

    /**
     * Metodo que testa atualizar um objeto Pauta Invalido na API
     *
     * @author Luiz Souza
     * @since 13/02/2023
     *
     * @throws Exception
     */
    @Test
    @Order(6)
    void testUpdateInvalidPauta() throws Exception {
        PautaNaoExisteEx ex = new PautaNaoExisteEx("Pauta id " + PAUTA_ID + " não existe.");
        BDDMockito.given(pautaService.findById(Mockito.any(UUID.class))).willThrow(ex).willReturn(new Pauta());

        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/{id}", PAUTA_ID).content(getJsonPayload(TITULO, DESCRICAO, SESSAO_VOTACAO_ID))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors.details").value("Pauta id " + PAUTA_ID + " não existe."));
    }

    /**
     * Metodo que testa encontrar todos os objetos Pauta na API
     *
     * @author Luiz Souza
     * @since 13/02/2023
     *
     * @throws Exception
     */
    @Test
    @Order(7)
    void testFindAllPauta() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * Método que preenche um objeto PautaDto falso para utilizar como retorno nos testes.
     *
     * @author Luiz Souza
     * @since 13/02/2023
     *
     * @param titulo
     * @param descricao
     * @return <code>String</code> com o payload PautaDto
     *
     * @throws JsonProcessingException
     */
    private String getJsonPayload(String titulo, String descricao, UUID sessaoVotacaoId) throws JsonProcessingException {

        PautaDTO dto = PautaDTO.builder().titulo(titulo).descricao(descricao).sessaoVotacaoId(sessaoVotacaoId).build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(dto);
    }
}
