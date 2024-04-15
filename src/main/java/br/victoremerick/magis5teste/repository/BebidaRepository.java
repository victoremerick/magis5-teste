package br.victoremerick.magis5teste.repository;

import br.victoremerick.magis5teste.model.entity.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BebidaRepository extends JpaRepository<Bebida, Long> {
}
