package com.example.projeto2springwebmvc.models;

import lombok.*;
import com.example.projeto2springwebmvc.models.enums.EstadoEncomenda;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_encomenda")
public class Encomenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idencomenda")
    private Integer idEncomenda;
    @Column(name = "datadepedido")
    private LocalDate dataDePedido;
    @Enumerated(EnumType.STRING)
    @Column(name = "estadoencomenda")
    private EstadoEncomenda estadoEncomenda;
    @ManyToOne
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "idFornecedor")
    private Fornecedor fornecedor;
    @ManyToOne
    @JoinColumn(name = "utilizador_id", referencedColumnName = "idUtilizador")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "linha_encomenda_id", referencedColumnName = "idLinhaEncomenda")
    private LinhaEncomenda linha_encomenda;
}
