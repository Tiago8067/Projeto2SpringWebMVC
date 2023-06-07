package com.example.projeto2springwebmvc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.example.projeto2springwebmvc.models.enums.EstadoUtilizador;
import com.example.projeto2springwebmvc.models.enums.TipoUtilizador;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_utilizador")
public class Utilizador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutilizador")
    private Integer idUtilizador;
    @Column(name = "email")
    private String email;
    // Validacoes com spring boot
    @NotEmpty
    @Size(min = 4, max = 40)
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "numerocc")
    private Integer numeroCc;
    @Column(name = "nif")
    private Integer nif;
    @Column(name = "nome")
    private String nome;

    @Column(name = "datanascimento")
    private String dataNascimento;
    @Column(name = "contacto")
    private Integer contacto;
    @Column(name = "imagemperfil")
    private String imagemPerfil;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoutilizador")
    private TipoUtilizador tipoUtilizador;
    @Enumerated(EnumType.STRING)
    @Column(name = "estadoutilizador")
    private EstadoUtilizador estadoUtilizador;
    @ManyToOne
    @JoinColumn(name = "localizacao_id", referencedColumnName = "idLocalizacao")
    @JsonIgnore
    private Localizacao localizacao;

    @OneToMany(mappedBy = "utilizador")
    @JsonIgnore
    private List<Doacao> doacoes;

    @OneToMany(mappedBy = "utilizador")
    @JsonIgnore
    private List<Encomenda> encomendas;
}
