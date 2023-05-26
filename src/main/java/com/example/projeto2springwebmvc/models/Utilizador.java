package com.example.projeto2springwebmvc.models;

import lombok.*;
import com.example.projeto2springwebmvc.models.enums.EstadoUtilizador;
import com.example.projeto2springwebmvc.models.enums.TipoUtilizador;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_utilizador")
public class Utilizador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilizador;
    private String email;
    private String username;
    private String password;
    private Integer numeroCc;
    private Integer nif;
    private String nome;

    // TODO => Acrescentar data no registo

    private Instant dataNascimento;
    private Integer contacto;
    private String imagemPerfil;
    @Enumerated(EnumType.STRING)
    private TipoUtilizador tipoUtilizador;
    @Enumerated(EnumType.STRING)
    private EstadoUtilizador estadoUtilizador;
    @ManyToOne
    @JoinColumn(name = "localizacao_id", referencedColumnName = "idLocalizacao")
    private Localizacao localizacao;

    @OneToMany(mappedBy = "utilizador")   //Faz ser bidirecional
    private List<Doacao> doacoes;

    @OneToMany(mappedBy = "utilizador")
    private List<Encomenda> encomendas;
}
