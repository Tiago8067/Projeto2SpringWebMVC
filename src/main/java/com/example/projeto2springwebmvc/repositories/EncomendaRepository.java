package com.example.projeto2springwebmvc.repositories;

import com.example.projeto2springwebmvc.models.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncomendaRepository extends JpaRepository<Encomenda, Integer> {
}
