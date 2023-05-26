package com.example.projeto2springwebmvc.repositories;

import com.example.projeto2springwebmvc.models.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
}
