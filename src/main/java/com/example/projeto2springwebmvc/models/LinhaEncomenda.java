package com.example.projeto2springwebmvc.models;

import lombok.*;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_linha_encomenda")
public class LinhaEncomenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlinhaencomenda")
    private Integer idLinhaEncomenda;
    @Column(name = "quantidade")
    private Integer quantidade;

    @OneToMany(mappedBy = "linha_encomenda")
    private List<Encomenda> encomendas;

    @OneToMany(mappedBy = "linha_encomenda")
    private List<RoupaDasEncomendas> roupaDasEncomendas;
}
