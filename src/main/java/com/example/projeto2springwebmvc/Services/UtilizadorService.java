package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.repositories.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;
}
