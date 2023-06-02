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

    public LinhaDoacoes(int idDoacao, String username, String tipoRoupa, String tamanhoRoupa, int quantidade) {
        this.idDoacao = idDoacao;
        this.username = username;
        this.tipoRoupa = tipoRoupa;
        this.tamanhoRoupa = tamanhoRoupa;
        this.quantidade = quantidade;
    }
}
