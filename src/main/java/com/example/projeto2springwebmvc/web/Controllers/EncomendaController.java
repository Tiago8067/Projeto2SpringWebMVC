package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.Services.EncomendaService;
import com.example.projeto2springwebmvc.models.Doacao;
import com.example.projeto2springwebmvc.modelsHelp.LinhaEncomendas;
import com.example.projeto2springwebmvc.repositories.EncomendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/encomendas")
public class EncomendaController {
    @Autowired
    private EncomendaService encomendaService;

    @GetMapping()
    public String mostraEncomendaList(Model model) {
        List<LinhaEncomendas> roupasEncomendadas = encomendaService.encomendasDasRoupas();
        model.addAttribute("roupasEncomendadas", roupasEncomendadas);
        return "listEncomendas";
    }

    @GetMapping("/addEncomenda")
    public String addEncomenda() {
        return "addEncomenda";
    }
}
