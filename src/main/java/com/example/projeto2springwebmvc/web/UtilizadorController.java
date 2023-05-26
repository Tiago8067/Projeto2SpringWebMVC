package com.example.projeto2springwebmvc.web;

import com.example.projeto2springwebmvc.models.Utilizador;
import com.example.projeto2springwebmvc.repositories.UtilizadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UtilizadorController {
    private UtilizadorRepository utilizadorRepository;

    @GetMapping(path = "/index")
    public String utilizadores(Model model) {
        List<Utilizador> utilizadors = utilizadorRepository.findAll();
        model.addAttribute("listUtilizadores", utilizadors);
        return "utilizadores";
    }
}
