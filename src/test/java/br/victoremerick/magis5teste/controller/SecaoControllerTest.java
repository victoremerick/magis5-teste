package br.victoremerick.magis5teste.controller;

import br.victoremerick.magis5teste.exceptions.CapacidadeExcedidaException;
import br.victoremerick.magis5teste.model.entity.Secao;
import br.victoremerick.magis5teste.model.enums.TipoHistorico;
import br.victoremerick.magis5teste.service.SecaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class SecaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecaoService secaoService;

    @Test
    public void testCreateSecao() throws Exception {
        Secao secao = new Secao();
        secao.setId(1L);
        secao.setNome("Secao 1");

        when(secaoService.save(any(Secao.class))).thenReturn(secao);

        mockMvc.perform(post("/api/secoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Secao 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Secao 1"));
    }

    @Test
    public void testAdicionarBebida_CapacidadeExcedida() throws Exception {
        doThrow(new CapacidadeExcedidaException("Capacidade da seção excedida"))
                .when(secaoService).adicionarBebida(eq(1L), eq(1L), eq(20), anyString(), eq(TipoHistorico.ENTRADA));

        mockMvc.perform(post("/api/secoes/1/adicionar-bebida/1")
                        .param("volume", "20")
                        .param("responsavel", "responsavel")
                        .param("tipo", "ENTRADA"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Capacidade da seção excedida"));
    }
}

