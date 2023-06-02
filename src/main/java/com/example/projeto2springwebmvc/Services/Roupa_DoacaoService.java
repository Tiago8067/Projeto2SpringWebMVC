package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Roupa_Doacao;
import com.example.projeto2springwebmvc.repositories.Roupa_DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Roupa_DoacaoService {
    @Autowired
    private Roupa_DoacaoRepository roupa_doacaoRepository;

    public void salvarRoupa_Doacao(Roupa_Doacao roupa_doacao) {
        roupa_doacaoRepository.save(roupa_doacao);
    }

    public Roupa_Doacao getRoupa_DoacaoPoId(Integer id) {
        Optional<Roupa_Doacao> result = roupa_doacaoRepository.findById(id);

        return result.orElse(null);
    }

    public List<Roupa_Doacao> roupa_doacaoList() {
        return roupa_doacaoRepository.findAll();
    }
}
