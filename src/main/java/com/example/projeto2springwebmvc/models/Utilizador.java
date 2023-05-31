package com.example.projeto2springwebmvc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.example.projeto2springwebmvc.models.enums.EstadoUtilizador;
import com.example.projeto2springwebmvc.models.enums.TipoUtilizador;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "idutilizador")
    private Integer idUtilizador;
    @Column(name = "email")
    private String email;
    // Validacoes com spring boot
    @NotEmpty
    @Size(min = 4, max = 40)
    /*@UniqueElements*/
    /*@DecimalMin("100")*/
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

    // TODO => Acrescentar data no registo
    /*@DateTimeFormat(pattern = "yyyy-MM-dd")*/

    @Column(name = "datanascimento")
    private Instant dataNascimento;
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

    @OneToMany(mappedBy = "utilizador")//Faz ser bidirecional
    @JsonIgnore
    private List<Doacao> doacoes;

    @OneToMany(mappedBy = "utilizador")
    @JsonIgnore
    private List<Encomenda> encomendas;
}
