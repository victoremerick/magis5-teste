package br.victoremerick.magis5teste.model.entity;

import br.victoremerick.magis5teste.model.enums.TipoBebida;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Secao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int capacidadeLitros;
    @Enumerated(EnumType.STRING)
    private TipoBebida tipoBebida;
    private int volumeAtual;
}
