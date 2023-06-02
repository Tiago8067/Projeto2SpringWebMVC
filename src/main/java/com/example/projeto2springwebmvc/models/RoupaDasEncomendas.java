package com.example.projeto2springwebmvc.models;

import lombok.*;
import com.example.projeto2springwebmvc.models.enums.TamanhoRoupa;
import com.example.projeto2springwebmvc.models.enums.TipoRoupa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_roupa_das_encomendas")
public class RoupaDasEncomendas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idroupadasencomendas")
    private Integer idroupaDasEncomendas;
    @Column(name = "datadeentrega")
    private Instant dataDeEntrega;
    @Enumerated(EnumType.STRING)
    @Column(name = "tamanhoroupa")
    private TamanhoRoupa tamanhoRoupa;
    @Enumerated(EnumType.STRING)
    @Column(name = "tiporoupa")
    private TipoRoupa tipoRoupa;

    @ManyToOne
    @JoinColumn(name = "linha_encomenda_id", referencedColumnName = "idLinhaEncomenda")
    private LinhaEncomenda linha_encomenda;
}
