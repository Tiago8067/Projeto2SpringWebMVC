package com.example.projeto2springwebmvc.repositories;

import com.example.projeto2springwebmvc.models.Utilizador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {
    Page<Utilizador> findByUsernameContains(String kw, Pageable pageable);
}
