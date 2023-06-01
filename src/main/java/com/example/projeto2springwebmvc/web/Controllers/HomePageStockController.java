package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.Services.RoupaService;
import com.example.projeto2springwebmvc.models.Roupa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageStockController {
    @Autowired
    private RoupaService roupaService;

    @GetMapping("/homePage")
    public String homePage(Model model) {
        List<Roupa> listaRoupas = roupaService.buscarTipoTamanhoUnico();
        model.addAttribute("listaRoupas", listaRoupas);

        return "stock";
    }
}
