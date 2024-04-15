package br.victoremerick.magis5teste.service;


import br.victoremerick.magis5teste.exceptions.CapacidadeExcedidaException;
import br.victoremerick.magis5teste.exceptions.SecaoNotFoundException;
import br.victoremerick.magis5teste.exceptions.VolumeInsuficienteException;
import br.victoremerick.magis5teste.model.entity.Secao;
import br.victoremerick.magis5teste.model.enums.TipoBebida;
import br.victoremerick.magis5teste.model.enums.TipoHistorico;
import br.victoremerick.magis5teste.repository.SecaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecaoService {
    private final SecaoRepository secaoRepository;
    private final HistoricoService historicoService;

    public Secao save(Secao secao) {
        return secaoRepository.save(secao);
    }

    public List<Secao> findAll() {
        return secaoRepository.findAll();
    }

    public Optional<Secao> findById(Long id) {
        return secaoRepository.findById(id);
    }

    public void adicionarBebida(Long secaoId, Long bebidaId, int volume, String responsavel, TipoHistorico tipo) {
        Secao secao = secaoRepository.findById(secaoId)
                .orElseThrow(() -> new SecaoNotFoundException("Seção não encontrada"));
        if (secao.getVolumeAtual() + volume > secao.getCapacidadeLitros()) {
            throw new CapacidadeExcedidaException("Capacidade da seção excedida");
        }

        if (tipo == TipoHistorico.ENTRADA) {
            secao.setVolumeAtual(secao.getVolumeAtual() + volume);
        } else if (tipo == TipoHistorico.SAIDA) {
            if (secao.getVolumeAtual() < volume) {
                throw new VolumeInsuficienteException("Volume insuficiente na seção");
            }
            secao.setVolumeAtual(secao.getVolumeAtual() - volume);
        }

        secaoRepository.save(secao);
        historicoService.registrarHistorico(secao, bebidaId, volume, responsavel, tipo);
    }

    public int calcularVolumeTotal(TipoBebida tipo) {
        return secaoRepository.findAllByTipoBebida(tipo).stream()
                .mapToInt(Secao::getVolumeAtual)
                .sum();
    }

    public List<Secao> encontrarSecoesDisponiveis(int volume, TipoBebida tipo) {
        return secaoRepository.findAllByTipoBebida(tipo).stream()
                .filter(secao -> secao.getVolumeAtual() + volume <= secao.getCapacidadeLitros())
                .collect(Collectors.toList());
    }

    public List<Secao> encontrarSecoesParaVenda(TipoBebida tipo) {
        return secaoRepository.findAllByTipoBebida(tipo).stream()
                .filter(secao -> secao.getVolumeAtual() > 0)
                .collect(Collectors.toList());
    }
}
