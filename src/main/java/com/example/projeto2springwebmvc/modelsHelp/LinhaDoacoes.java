package com.example.projeto2springwebmvc.modelsHelp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinhaDoacoes {
    private Integer idDoacao;
    private String username;
    private String tipoRoupa;
    private String tamanhoRoupa;
    private Integer quantidade;
    private String dataDoacao;

    public LinhaDoacoes(int idDoacao, String username, String tipoRoupa, String tamanhoRoupa, int quantidade, String dataDoacao) {
        this.idDoacao = idDoacao;
        this.username = username;
        this.tipoRoupa = tipoRoupa;
        this.tamanhoRoupa = tamanhoRoupa;
        this.quantidade = quantidade;
        this.dataDoacao = dataDoacao;
    }
}
