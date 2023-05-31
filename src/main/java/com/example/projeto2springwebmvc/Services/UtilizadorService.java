package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Utilizador;
import com.example.projeto2springwebmvc.models.enums.EstadoUtilizador;
import com.example.projeto2springwebmvc.models.enums.TipoUtilizador;
import com.example.projeto2springwebmvc.repositories.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public List<Utilizador> utilizadorList() {
        return utilizadorRepository.findAll();
    }

    public void salvarCliente(Utilizador utilizador) {
        utilizador.setEstadoUtilizador(EstadoUtilizador.ATIVO);
        utilizador.setTipoUtilizador(TipoUtilizador.CLIENTE);
        utilizadorRepository.save(utilizador);
    }
}
