package br.victoremerick.magis5teste.repository;

import br.victoremerick.magis5teste.model.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}
