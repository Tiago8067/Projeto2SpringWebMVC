package com.example.projeto2springwebmvc.repositories;

import com.example.projeto2springwebmvc.models.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {
}
