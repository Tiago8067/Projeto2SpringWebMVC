package com.example.projeto2springwebmvc.models;

import lombok.*;
import com.example.projeto2springwebmvc.models.enums.CategoriaRoupa;
import com.example.projeto2springwebmvc.models.enums.TamanhoRoupa;
import com.example.projeto2springwebmvc.models.enums.TipoRoupa;

import jakarta.persistence.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_roupa")
public class Roupa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idroupa")
    //@Column(unique = true, updatable = false)
    private Integer idRoupa;
    @Column(name = "imagesrc")
    private String imageSrc;
    @Column(name = "stock")
    private Integer stock;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoriaroupa")
    private CategoriaRoupa categoriaRoupa;
    @Enumerated(EnumType.STRING)
    @Column(name = "tamanhoroupa")
    private TamanhoRoupa tamanhoRoupa;
    @Enumerated(EnumType.STRING)
    @Column(name = "tiporoupa")
    private TipoRoupa tipoRoupa;

    @ManyToOne
    @JoinColumn(name = "roupa_doacao_id", referencedColumnName = "id_roupa_doacao")
    private Roupa_Doacao roupa_doacao;
}
