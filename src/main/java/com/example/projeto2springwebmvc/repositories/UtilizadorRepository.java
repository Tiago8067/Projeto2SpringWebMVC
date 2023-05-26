package com.example.projeto2springwebmvc.repositories;

import com.example.projeto2springwebmvc.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {
}
