package br.victoremerick.magis5teste.service;

import br.victoremerick.magis5teste.exceptions.CapacidadeExcedidaException;
import br.victoremerick.magis5teste.exceptions.SecaoNotFoundException;
import br.victoremerick.magis5teste.model.entity.Secao;
import br.victoremerick.magis5teste.model.enums.TipoBebida;
import br.victoremerick.magis5teste.model.enums.TipoHistorico;
import br.victoremerick.magis5teste.repository.SecaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecaoServiceTest {

    @InjectMocks
    private SecaoService secaoService;

    @Mock
    private SecaoRepository secaoRepository;

    @Mock
    private HistoricoService historicoService;

    @Test
    public void testAdicionarBebida_CapacidadeExcedida() {
        Secao secao = new Secao();
        secao.setId(1L);
        secao.setCapacidadeLitros(500);
        secao.setVolumeAtual(490);

        when(secaoRepository.findById(1L)).thenReturn(Optional.of(secao));

        assertThrows(CapacidadeExcedidaException.class, () -> {
            secaoService.adicionarBebida(1L, 1L, 20, "responsavel", TipoHistorico.ENTRADA);
        });
    }

    @Test
    public void testAdicionarBebida_SecaoNotFound() {
        when(secaoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SecaoNotFoundException.class, () -> {
            secaoService.adicionarBebida(1L, 1L, 10, "responsavel", TipoHistorico.ENTRADA);
        });
    }

    @Test
    public void testCalcularVolumeTotal() {
        Secao secao1 = new Secao();
        secao1.setTipoBebida(TipoBebida.ALCOOLICA);
        secao1.setVolumeAtual(300);

        Secao secao2 = new Secao();
        secao2.setTipoBebida(TipoBebida.ALCOOLICA);
        secao2.setVolumeAtual(200);

        when(secaoRepository.findAllByTipoBebida(any(TipoBebida.class))).thenReturn(Arrays.asList(secao1, secao2));

        int volumeTotal = secaoService.calcularVolumeTotal(TipoBebida.ALCOOLICA);
        assertEquals(500, volumeTotal);
    }

}

