package br.victoremerick.magis5teste.service;

import br.victoremerick.magis5teste.exceptions.BebidaNotFoundException;
import br.victoremerick.magis5teste.model.entity.Bebida;
import br.victoremerick.magis5teste.model.entity.Historico;
import br.victoremerick.magis5teste.model.entity.Secao;
import br.victoremerick.magis5teste.model.enums.TipoHistorico;
import br.victoremerick.magis5teste.repository.HistoricoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoricoService {
    private final HistoricoRepository historicoRepository;
    private final BebidaService bebidaService;

    public Historico save(Historico historico) {
        return historicoRepository.save(historico);
    }

    public List<Historico> findAll() {
        return historicoRepository.findAll();
    }

    public List<Historico> findByTipoAndSecaoOrderByDataAsc(TipoHistorico tipo, Long secaoId) {
        return historicoRepository.findAll().stream()
                .filter(h -> h.getTipo() == tipo && h.getSecao().getId().equals(secaoId))
                .sorted(Comparator.comparing(Historico::getData))
                .collect(Collectors.toList());
    }

    public void registrarHistorico(Secao secao, Long bebidaId, int volume, String responsavel, TipoHistorico tipo) {
        Bebida bebida = bebidaService.findById(bebidaId)
                .orElseThrow(() -> new BebidaNotFoundException("Bebida não encontrada"));
        Historico historico = new Historico();
        historico.setSecao(secao);
        historico.setBebida(bebida);
        historico.setVolume(volume);
        historico.setData(new Timestamp(System.currentTimeMillis()));
        historico.setResponsavel(responsavel);
        historico.setTipo(tipo);
        historicoRepository.save(historico);
    }
}

