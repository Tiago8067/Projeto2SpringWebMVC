package com.example.projeto2springwebmvc.repositories;

import com.example.projeto2springwebmvc.models.Roupa;
import com.example.projeto2springwebmvc.models.Roupa_Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoupaRepository extends JpaRepository<Roupa, Integer> {
    /*Roupa findByRoupa_doacao(Roupa_Doacao roupa_doacao);*/
    //Roupa findByRoupa_doacao(Integer id);
}
