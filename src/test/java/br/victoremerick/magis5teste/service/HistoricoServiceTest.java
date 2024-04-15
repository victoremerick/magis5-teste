package br.victoremerick.magis5teste.service;

import br.victoremerick.magis5teste.exceptions.BebidaNotFoundException;
import br.victoremerick.magis5teste.model.entity.Secao;
import br.victoremerick.magis5teste.model.enums.TipoHistorico;
import br.victoremerick.magis5teste.repository.HistoricoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class HistoricoServiceTest {

    @InjectMocks
    private HistoricoService historicoService;

    @Mock
    private HistoricoRepository historicoRepository;

    @Mock
    private BebidaService bebidaService;

    @Test
    public void testRegistrarHistorico_BebidaNotFound() {
        Secao secao = new Secao();

        assertThrows(BebidaNotFoundException.class, () -> {
            historicoService.registrarHistorico(secao, 1L, 100, "responsavel", TipoHistorico.ENTRADA);
        });
    }

}

