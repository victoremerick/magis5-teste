package br.victoremerick.magis5teste.repository;

import br.victoremerick.magis5teste.model.entity.Secao;
import br.victoremerick.magis5teste.model.enums.TipoBebida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecaoRepository extends JpaRepository<Secao, Long> {
    List<Secao> findAllByTipoBebida(TipoBebida tipoBebida);
}
