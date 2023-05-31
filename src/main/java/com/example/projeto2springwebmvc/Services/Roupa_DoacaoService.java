package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Roupa_Doacao;
import com.example.projeto2springwebmvc.repositories.Roupa_DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Roupa_DoacaoService {
    @Autowired
    private Roupa_DoacaoRepository roupa_doacaoRepository;

    public void salvarRoupa_Doacao(Roupa_Doacao roupa_doacao) {
        roupa_doacaoRepository.save(roupa_doacao);
    }
}
