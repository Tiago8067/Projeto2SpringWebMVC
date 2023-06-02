package com.example.projeto2springwebmvc.repositories;

import com.example.projeto2springwebmvc.models.Doacao;
import com.example.projeto2springwebmvc.models.Roupa_Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Roupa_DoacaoRepository extends JpaRepository<Roupa_Doacao, Integer> {
    Roupa_Doacao findByDoacoes(Doacao doacao);
}
