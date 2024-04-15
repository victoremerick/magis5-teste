package br.victoremerick.magis5teste.controller;

import br.victoremerick.magis5teste.model.entity.Bebida;
import br.victoremerick.magis5teste.service.BebidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bebidas")
@RequiredArgsConstructor
public class BebidaController {
    private final BebidaService bebidaService;

    @PostMapping
    public ResponseEntity<Bebida> createBebida(@RequestBody Bebida bebida) {
        return ResponseEntity.ok(bebidaService.save(bebida));
    }

    @GetMapping
    public ResponseEntity<List<Bebida>> getAllBebidas() {
        return ResponseEntity.ok(bebidaService.findAll());
    }
}
