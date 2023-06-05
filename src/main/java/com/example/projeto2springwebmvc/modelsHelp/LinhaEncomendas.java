package com.example.projeto2springwebmvc.modelsHelp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinhaEncomendas {
    private Integer idEncomenda;
    private Integer idLinhaEncomenda;
    private Integer idRoupa;
    private String usernameCliente;
    private String usernameFonecedor;
    private String tipoRoupa;
    private String tamanhoRoupa;
    private Integer quantidade;
    private String estado;
    private String dataPedido;
    private String dataEntrega;

    public LinhaEncomendas(int idEncomenda, int idLinhaEncomenda, int idRoupa, String usernameCliente, String usernameFonecedor, String tipoRoupa, String tamanhoRoupa, int quantidade, String estado, String dataPedido, String dataEntrega) {
        this.idEncomenda = idEncomenda;
        this.idLinhaEncomenda = idLinhaEncomenda;
        this.idRoupa = idRoupa;
        this.usernameCliente = usernameCliente;
        this.usernameFonecedor = usernameFonecedor;
        this.tipoRoupa = tipoRoupa;
        this.tamanhoRoupa = tamanhoRoupa;
        this.quantidade = quantidade;
        this.estado = estado;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
    }
}
