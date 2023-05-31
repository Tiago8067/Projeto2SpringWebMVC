package com.example.projeto2springwebmvc.models;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfornecedor")
    private Integer idFornecedor;
    @Column(name = "nome")
    private String nome;
    @Column(name = "contacto")
    private Integer contacto;
    @ManyToOne
    @JoinColumn(name = "localizacao_id", referencedColumnName = "idLocalizacao")
    private Localizacao localizacao;

    @OneToMany(mappedBy = "fornecedor")
    private List<Encomenda> encomendas;
}
