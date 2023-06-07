package com.example.projeto2springwebmvc.util;

import com.example.projeto2springwebmvc.models.enums.CategoriaRoupa;
import com.example.projeto2springwebmvc.models.enums.TipoRoupa;
import lombok.NoArgsConstructor;

import static com.example.projeto2springwebmvc.models.enums.TipoRoupa.*;

@NoArgsConstructor
public class HelpAddDoacoes {
    public CategoriaRoupa adicionarAssociarCategoria(TipoRoupa tipoRoupa) {
        if (tipoRoupa.equals(TipoRoupa.CALCOES) || tipoRoupa.equals(CALCAS) ||
                tipoRoupa.equals(TipoRoupa.SAIA) || tipoRoupa.equals(TipoRoupa.MEIAS) || tipoRoupa.equals(TipoRoupa.MEIACALCA)) {
            return CategoriaRoupa.PARTEDEBAIXO;
        } else {
            return CategoriaRoupa.PARTEDECIMA;
        }
    }

    public String adicionarAssociarImagem(TipoRoupa tipoRoupa) {
        return switch (tipoRoupa) {
            case CALCAS -> "/images/calcas.jpg";
            case CALCOES -> "/images/calcoes.jpg";
            case VESTIDO -> "/images/vestido.jpg";
            case SAIA -> "/images/saia.jpg";
            case BLUSA -> "/images/blusa.jpg";
            case SWEAT -> "/images/sweat.jpg";
            case T_SHIRT -> "/images/tshirt.jpg";
            case CAMISA -> "/images/camisa.jpg";
            case CASACO -> "/images/casaco.jpg";
            case COLETE -> "/images/colete.jpg";
            case MEIACALCA -> "/images/meia_calca.jpg";
            case MEIAS -> "/images/meias.jpg";
        };
    }
}
