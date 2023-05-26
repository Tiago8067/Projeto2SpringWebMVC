package com.example.projeto2springwebmvc.repositories;

import com.example.projeto2springwebmvc.models.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {
}
