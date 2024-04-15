package br.victoremerick.magis5teste.controller;


import br.victoremerick.magis5teste.model.entity.Secao;
import br.victoremerick.magis5teste.model.enums.TipoBebida;
import br.victoremerick.magis5teste.model.enums.TipoHistorico;
import br.victoremerick.magis5teste.service.SecaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secoes")
@RequiredArgsConstructor
public class SecaoController {
    private final SecaoService secaoService;

    @PostMapping
    public ResponseEntity<Secao> createSecao(@RequestBody Secao secao) {
        return ResponseEntity.ok(secaoService.save(secao));
    }

    @GetMapping
    public ResponseEntity<List<Secao>> getAllSecoes() {
        return ResponseEntity.ok(secaoService.findAll());
    }

    @PostMapping("/{secaoId}/adicionar-bebida/{bebidaId}")
    public ResponseEntity<Void> adicionarBebida(
            @PathVariable Long secaoId,
            @PathVariable Long bebidaId,
            @RequestParam int volume,
            @RequestParam String responsavel,
            @RequestParam TipoHistorico tipo) {
        secaoService.adicionarBebida(secaoId, bebidaId, volume, responsavel, tipo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/volume-total")
    public ResponseEntity<Integer> calcularVolumeTotal(@RequestParam TipoBebida tipo) {
        return ResponseEntity.ok(secaoService.calcularVolumeTotal(tipo));
    }

    @GetMapping("/secoes-disponiveis")
    public ResponseEntity<List<Secao>> encontrarSecoesDisponiveis(@RequestParam int volume, @RequestParam TipoBebida tipo) {
        return ResponseEntity.ok(secaoService.encontrarSecoesDisponiveis(volume, tipo));
    }

    @GetMapping("/secoes-para-venda")
    public ResponseEntity<List<Secao>> encontrarSecoesParaVenda(@RequestParam TipoBebida tipo) {
        return ResponseEntity.ok(secaoService.encontrarSecoesParaVenda(tipo));
    }
}
