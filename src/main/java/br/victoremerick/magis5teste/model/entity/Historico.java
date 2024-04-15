package br.victoremerick.magis5teste.model.entity;

import br.victoremerick.magis5teste.model.enums.TipoHistorico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoHistorico tipo;
    private int volume;
    private Timestamp data;
    @ManyToOne
    private Secao secao;
    @ManyToOne
    private Bebida bebida;
    private String responsavel;
}
