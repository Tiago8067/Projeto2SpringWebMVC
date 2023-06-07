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

    /*public String adicionarAssociarImagem(TipoRoupa tipoRoupa) {
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
    }*/
    public String adicionarAssociarImagem(TipoRoupa tipoRoupa) {
        return switch (tipoRoupa) {
            case CALCAS -> "/images/images2/calcas.gif";
            case CALCOES -> "/images/images2/calcoes.gif";
            case VESTIDO -> "/images/images2/vestido.gif";
            case SAIA -> "/images/images2/saia2.jfif";
            case BLUSA -> "/images/images2/blusa.gif";
            case SWEAT -> "/images/images2/sweat.gif";
            case T_SHIRT -> "/images/images2/t-shirt.gif";
            case CAMISA -> "/images/images2/camisa.png";
            case CASACO -> "/images/images2/casaco.gif";
            case COLETE -> "/images/images2/colete.gif";
            case MEIACALCA -> "/images/images2/meia-calca.gif";
            case MEIAS -> "/images/images2/meias.gif";
        };
    }
}
