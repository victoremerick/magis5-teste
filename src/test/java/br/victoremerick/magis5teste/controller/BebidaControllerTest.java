package br.victoremerick.magis5teste.controller;

import br.victoremerick.magis5teste.model.entity.Bebida;
import br.victoremerick.magis5teste.service.BebidaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class BebidaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BebidaService bebidaService;

    @Test
    public void testCreateBebida() throws Exception {
        Bebida bebida = new Bebida();
        bebida.setId(1L);
        bebida.setNome("Cerveja");

        when(bebidaService.save(any(Bebida.class))).thenReturn(bebida);

        mockMvc.perform(post("/api/bebidas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Cerveja\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Cerveja"));
    }
}

