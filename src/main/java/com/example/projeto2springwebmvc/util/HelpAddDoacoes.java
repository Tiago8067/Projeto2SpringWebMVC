package com.example.projeto2springwebmvc.util;

import com.example.projeto2springwebmvc.models.enums.CategoriaRoupa;
import com.example.projeto2springwebmvc.models.enums.TipoRoupa;
import lombok.NoArgsConstructor;

import static com.example.projeto2springwebmvc.models.enums.TipoRoupa.*;

@NoArgsConstructor
public class HelpAddDoacoes {
    public CategoriaRoupa adicionarAssociarCategoria(TipoRoupa tipoRoupa) {
        if (tipoRoupa.equals(TipoRoupa.CALCOES) || tipoRoupa.equals(TipoRoupa.BERMUDAS) || tipoRoupa.equals(CALCAS) ||
                tipoRoupa.equals(TipoRoupa.SAIA) || tipoRoupa.equals(TipoRoupa.MEIAS) || tipoRoupa.equals(TipoRoupa.MEIACALCA)) {
            return CategoriaRoupa.PARTEDEBAIXO;
        } else if (tipoRoupa.equals(TipoRoupa.BLUSA) || tipoRoupa.equals(TipoRoupa.VESTIDO) || tipoRoupa.equals(TipoRoupa.SWEAT) ||
                tipoRoupa.equals(TipoRoupa.T_SHIRT) || tipoRoupa.equals(TipoRoupa.CAMISA) || tipoRoupa.equals(TipoRoupa.CASACO) ||
                tipoRoupa.equals(TipoRoupa.COLETE)) {
            return CategoriaRoupa.PARTEDECIMA;
        } else {
            return CategoriaRoupa.ACESSORIOS;
        }
    }

    public String adicionarAssociarImagem(TipoRoupa tipoRoupa) {
        return switch (tipoRoupa) {
            case CALCAS -> "/images/calcas.jpg";
            case CALCOES -> "/images/calcoes.jpg";
            case BERMUDAS -> "/images/bermudas.jpg";
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
            case SAPATOSCLASSICO -> "/images/sapato_classico.jpg";
            case SAPATOSDESPORTIVO -> "/images/sapato_desportivo.jpg";
            case BOLSA -> "/images/bolsa.jpg";
        };
    }
}
