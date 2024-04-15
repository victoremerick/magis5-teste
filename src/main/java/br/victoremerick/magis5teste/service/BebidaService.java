package br.victoremerick.magis5teste.service;

import br.victoremerick.magis5teste.model.entity.Bebida;
import br.victoremerick.magis5teste.repository.BebidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BebidaService {
    private final BebidaRepository bebidaRepository;

    public Bebida save(Bebida bebida) {
        return bebidaRepository.save(bebida);
    }

    public List<Bebida> findAll() {
        return bebidaRepository.findAll();
    }

    public Optional<Bebida> findById(Long id) {
        return bebidaRepository.findById(id);
    }
}

