package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Roupa;
import com.example.projeto2springwebmvc.repositories.RoupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoupaService {
    @Autowired
    private RoupaRepository roupaRepository;

    public void salvarRoupa(Roupa roupa) {
        roupaRepository.save(roupa);
    }
}
