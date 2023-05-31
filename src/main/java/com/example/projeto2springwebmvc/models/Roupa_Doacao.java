package com.example.projeto2springwebmvc.models;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_roupa_doacao")
public class Roupa_Doacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_roupa_doacao")
    private Integer id_roupa_doacao;
    @Column(name = "quantidade")
    private Integer quantidade;

    @OneToMany(mappedBy = "roupa_doacao")
    private List<Doacao> doacoes;

    @OneToMany(mappedBy = "roupa_doacao")
    private List<Roupa> roupas;
}
