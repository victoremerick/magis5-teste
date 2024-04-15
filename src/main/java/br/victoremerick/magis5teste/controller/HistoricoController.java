package br.victoremerick.magis5teste.controller;

import br.victoremerick.magis5teste.model.entity.Historico;
import br.victoremerick.magis5teste.model.enums.TipoHistorico;
import br.victoremerick.magis5teste.service.HistoricoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historicos")
@RequiredArgsConstructor
public class HistoricoController {
    private final HistoricoService historicoService;

    @PostMapping
    public ResponseEntity<Historico> createHistorico(@RequestBody Historico historico) {
        return ResponseEntity.ok(historicoService.save(historico));
    }

    @GetMapping
    public ResponseEntity<List<Historico>> getAllHistoricos() {
        return ResponseEntity.ok(historicoService.findAll());
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<Historico>> findByTipoAndSecaoOrderByDataAsc(
            @RequestParam TipoHistorico tipo,
            @RequestParam Long secaoId) {
        return ResponseEntity.ok(historicoService.findByTipoAndSecaoOrderByDataAsc(tipo, secaoId));
    }
}
