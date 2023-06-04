package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.Services.RoupaService;
import com.example.projeto2springwebmvc.Services.UtilizadorService;
import com.example.projeto2springwebmvc.models.Roupa;
import com.example.projeto2springwebmvc.models.Utilizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Controller
public class HomePageController {
    @Autowired
    private RoupaService roupaService;

    @Autowired
    private UtilizadorService utilizadorService;
    String guardaUsernameLogin = UtilizadorAutenticacaoController.utilizadorUsernameLogado;

    @GetMapping("/homePage")
    public String homePage(Model model) {
        List<Roupa> listaRoupas = roupaService.buscarTipoTamanhoUnico();
        model.addAttribute("listaRoupas", listaRoupas);

        return "stock";
    }

    @GetMapping("/perfilPage")
    public String perfilPage(Model model) {
        Utilizador userLogado = this.utilizadorService.retornaUtilizadorLogado(guardaUsernameLogin);
        model.addAttribute("userLogado", userLogado);
        return "perfilPage";
    }
}
