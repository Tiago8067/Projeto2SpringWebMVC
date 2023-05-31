package com.example.projeto2springwebmvc.models;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_doacao")
public class Doacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddoacao")
    private Integer idDoacao;
    @Column(name = "datadadoacao")
    private Instant dataDaDoacao;
    @ManyToOne
    @JoinColumn(name = "utilizador_id", referencedColumnName = "idUtilizador")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "roupa_doacao_id", referencedColumnName = "id_roupa_doacao")
    private Roupa_Doacao roupa_doacao;
}
