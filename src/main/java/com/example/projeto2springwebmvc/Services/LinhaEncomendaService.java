package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.LinhaEncomenda;
import com.example.projeto2springwebmvc.repositories.LinhaEncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinhaEncomendaService {
    @Autowired
    private LinhaEncomendaRepository linhaEncomendaRepository;

    public void salvarLinhaEncomenda(LinhaEncomenda linhaEncomenda) {
        linhaEncomendaRepository.save(linhaEncomenda);
    }
}
