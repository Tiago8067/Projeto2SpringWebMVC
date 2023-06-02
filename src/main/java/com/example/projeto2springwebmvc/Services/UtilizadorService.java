package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Utilizador;
import com.example.projeto2springwebmvc.models.enums.EstadoUtilizador;
import com.example.projeto2springwebmvc.models.enums.TipoUtilizador;
import com.example.projeto2springwebmvc.repositories.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public void salvarCliente(Utilizador utilizador) {
        utilizador.setEstadoUtilizador(EstadoUtilizador.ATIVO);
        utilizador.setTipoUtilizador(TipoUtilizador.CLIENTE);
        utilizadorRepository.save(utilizador);
    }

    public Optional<Utilizador> verificaDadosLogin(String username, String password) {
        return utilizadorRepository.findUtilizadorByUsernameAndPassword(username, password);
    }

    public Utilizador retornaUtilizadorLogado(String username) {
        return utilizadorRepository.findUtilizadorByUsername(username);
    }
}
