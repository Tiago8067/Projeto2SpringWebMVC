package com.example.projeto2springwebmvc.web.Controllers;

import com.example.projeto2springwebmvc.Services.DoacaoService;
import com.example.projeto2springwebmvc.models.Doacao;
import com.example.projeto2springwebmvc.modelsHelp.LinhaDoacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/doacoes")
public class DoacaoController {
    @Autowired
    private DoacaoService doacaoService;

    @GetMapping()
    public String mostraDoacaoList(Model model) {
        //List<Doacao> doacaoList = doacaoService.doacaoList();
        List<LinhaDoacoes> roupasDoadas = doacaoService.doacoesDasRoupas();
        //model.addAttribute("doacaoList", doacaoList);
        model.addAttribute("roupasDoadas", roupasDoadas);
        return "listDoacoes";
    }

    @GetMapping("/addDoacao")
    public String addDoacao() {
        return "addDoacao";
    }
}
