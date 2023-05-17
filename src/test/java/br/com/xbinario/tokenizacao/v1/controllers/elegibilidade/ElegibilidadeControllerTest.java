package br.com.xbinario.tokenizacao.v1.controllers.elegibilidade;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.RequestElegibilidadeDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.ResponseELegibilidadeDTO;
import br.com.xbinario.tokenizacao.v1.services.elegibilidade.ElegibilidadeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest()
@ExtendWith(MockitoExtension.class)
public class ElegibilidadeControllerTest {

    @InjectMocks
    ElegibilidadeController resource;

    @Mock
    private ElegibilidadeService service;

    @Value("${api.test.endpoint.elegibilidade.check}")
    private String endPoint;

    @Value("${api.test.resquest.elegibilidade.json}")
    String requestElegibilidadeJson;

    @Value("${api.test.response.elegibilidade.json}")
    String responseElegibilidadeJson;

    

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(resource).build();
    }

    @Test
    public void test_RequisicaoElegibilidade_Status_200() throws Exception {

        var request = readTestFile(requestElegibilidadeJson);
        log.info("#### Carregando requisição: {}", request);

        var response = mapper.readValue(readTestFile(responseElegibilidadeJson), ResponseELegibilidadeDTO.class);
        log.info("#### Carregando resultado: {}", response);

        when(service.check(Mockito.<RequestElegibilidadeDTO>any())).thenReturn(response);

        var result = mockMvc
                                .perform(MockMvcRequestBuilders
                                .post(this.endPoint)
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.parseMediaType("application/json")))
                                .andExpect(MockMvcResultMatchers.status().is(200))
                                .andReturn();

        var actual = mapper.readValue(result.getResponse().getContentAsString(), ResponseELegibilidadeDTO.class);

        var expectedJson = mapper.valueToTree(response);
        var actualJson = mapper.valueToTree(actual);

        assertEquals(expectedJson, actualJson);     
        
    }


    @Test
    public void test_RequisicaoElegibilidade_Status_400_CPFCNPJ() throws Exception {

        var requestJson="elegibilidade/ElegibilidadeResquestFalha_cpfCnpj.json";
        var request = readTestFile(requestJson);
        log.info("#### Carregando requisição: {}", request);

        var response = mapper.readValue(readTestFile(responseElegibilidadeJson), ResponseELegibilidadeDTO.class);
        log.info("#### Carregando resultado: {}", response);

        mockMvc.perform(MockMvcRequestBuilders
                .post(this.endPoint)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();

    }


    @Test
    public void test_RequisicaoElegibilidade_Status_400_Processadora() throws Exception {

        var requestJson="elegibilidade/ElegibilidadeResquestFalha_processadora.json";
        var request = readTestFile(requestJson);
        log.info("#### Carregando requisição: {}", request);

        var response = mapper.readValue(readTestFile(responseElegibilidadeJson), ResponseELegibilidadeDTO.class);
        log.info("#### Carregando resultado: {}", response);

        mockMvc.perform(MockMvcRequestBuilders
                .post(this.endPoint)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();

    }


    @Test
    public void test_RequisicaoElegibilidade_Status_400_QuanidadeCartao() throws Exception {

        var requestJson="elegibilidade/ElegibilidadeResquestFalha_quantidadeCartoes.json";
        var request = readTestFile(requestJson);
        log.info("#### Carregando requisição: {}", request);

        var response = mapper.readValue(readTestFile(responseElegibilidadeJson), ResponseELegibilidadeDTO.class);
        log.info("#### Carregando resultado: {}", response);

        mockMvc.perform(MockMvcRequestBuilders
                .post(this.endPoint)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();

    }



    private String readTestFile(String file) throws IOException {
        var fileName = file;
        var inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Arquivo não encontrado: " + fileName);
        }
        var fileContent = new String(inputStream.readAllBytes());
        return fileContent;
    }    

}
