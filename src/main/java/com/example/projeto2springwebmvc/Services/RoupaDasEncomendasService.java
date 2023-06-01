package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.RoupaDasEncomendas;
import com.example.projeto2springwebmvc.repositories.RoupaDasEncomendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoupaDasEncomendasService {
    @Autowired
    private RoupaDasEncomendasRepository roupaDasEncomendasRepository;

    public void salvarRoupaDasEncomendas(RoupaDasEncomendas roupaDasEncomendas) {
        roupaDasEncomendasRepository.save(roupaDasEncomendas);
    }
}
