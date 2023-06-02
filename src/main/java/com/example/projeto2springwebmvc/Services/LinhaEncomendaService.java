package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.LinhaEncomenda;
import com.example.projeto2springwebmvc.models.Roupa_Doacao;
import com.example.projeto2springwebmvc.repositories.LinhaEncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinhaEncomendaService {
    @Autowired
    private LinhaEncomendaRepository linhaEncomendaRepository;

    public void salvarLinhaEncomenda(LinhaEncomenda linhaEncomenda) {
        linhaEncomendaRepository.save(linhaEncomenda);
    }

    public List<LinhaEncomenda> linhaEncomendaList(){
        return linhaEncomendaRepository.findAll();
    }

    public LinhaEncomenda getLinhaEncomendaPorId(Integer id) {
        Optional<LinhaEncomenda> result = linhaEncomendaRepository.findById(id);

        return result.orElse(null);
    }

}
