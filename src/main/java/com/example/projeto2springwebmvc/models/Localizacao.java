package com.example.projeto2springwebmvc.models;

import lombok.*;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_localizacao")
public class Localizacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlocalizacao")
    private Integer idLocalizacao;
    @Column(name = "codigopostal")
    private String codigoPostal;
    @Column(name = "localidade")
    private String localidade;
    @Column(name = "rua")
    private String rua;
    @Column(name = "numeroporta")
    private Integer numeroPorta;
    @Column(name = "cidade")
    private String cidade;

    @OneToMany(mappedBy = "localizacao")
    private List<Fornecedor> fornecedores;

    @OneToMany(mappedBy = "localizacao")
    private List<Utilizador> utilizadores;
}
