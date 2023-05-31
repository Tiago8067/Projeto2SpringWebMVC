package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Localizacao;
import com.example.projeto2springwebmvc.repositories.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {
    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public void salvarLocalizacao(Localizacao localizacao) {
        localizacaoRepository.save(localizacao);
    }
}
